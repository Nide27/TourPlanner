package org.group07.tourplanner.app.viewmodel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.group07.tourplanner.app.Main;
import org.group07.tourplanner.app.helper.ResourceManager;
import org.group07.tourplanner.bl.PdfGenerator;
import org.group07.tourplanner.dal.DAL;
import org.group07.tourplanner.dal.Jackson;
import org.group07.tourplanner.dal.TourItemDao;
import org.group07.tourplanner.dal.TourLogDao;
import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.dal.model.TourLog;
import org.group07.tourplanner.dal.model.TourReport;
import org.group07.tourplanner.dal.model.TourSummary;

public class TopBarViewModel {

    private static TourItem tourItem;

    private final TourOverviewViewModel tourOverviewViewModel;

    private final ResourceManager rm;


    public TopBarViewModel(TourOverviewViewModel tourOverviewViewModel){
        this.tourOverviewViewModel = tourOverviewViewModel;
        this.rm = ResourceManager.getInstance();
    }

    public void setTourItem(TourItem tourItem){

        if(tourItem == null)
            return;

        this.tourItem = tourItem;
    }

    public void createSummarizedReport(){

        List<TourItem> tourItemList = DAL.getInstance().getTourItemDao().getAll();

        List<TourSummary> tourSummaryList = new ArrayList<>();

        tourItemList.forEach(tourItem -> {
            List<TourLog> tourLogList = DAL.getInstance().getTourLogDao().getAllById(tourItem.getId());

            ListIterator<TourLog> it = tourLogList.listIterator();

            double avgTime = 0;
            double avgDifficulty = 0;
            double avgRating = 0;

            while(it.hasNext()){
                TourLog entry = it.next();

                avgTime += entry.getDuration();
                avgDifficulty += entry.getDifficulty();
                avgRating += entry.getRating();
            }

            if(tourLogList.size() > 0){
                avgTime /= tourLogList.size();

                avgDifficulty /= tourLogList.size();

                avgRating /= tourLogList.size();
            }

            tourSummaryList.add(new TourSummary(tourItem.getName(), avgTime, avgDifficulty, avgRating));
        });

        DirectoryChooser dr = new DirectoryChooser();
        dr.setTitle(rm.load("FILE_EXPORT_TITLE"));
        File dir = dr.showDialog(new Stage());

        String path = dir.getAbsolutePath() + File.separator + "summary.pdf";

        PdfGenerator pdfGenerator = new PdfGenerator();
        String html = pdfGenerator.parseSummarizedTemplate(tourSummaryList);
        pdfGenerator.generatePdfFromHtml(html, path);
    }

    public void createTourReport(){

        if(this.tourItem == null)
            return;

        List<TourLog> tourLogList = DAL.getInstance().getTourLogDao().getAllById(this.tourItem.getId());

        DirectoryChooser dr = new DirectoryChooser();
        dr.setTitle(rm.load("FILE_EXPORT_TITLE"));
        File dir = dr.showDialog(new Stage());

        String path = dir.getAbsolutePath() + File.separator + tourItem.getName() + ".pdf";

        PdfGenerator pdfGenerator = new PdfGenerator();
        String html = pdfGenerator.parseTourTemplate(new TourReport(this.tourItem, tourLogList));
        pdfGenerator.generatePdfFromHtml(html, path);
    }

    public void importFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(rm.load("FILE_IMPORT_TITLE"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));
        File file = fileChooser.showOpenDialog(new Stage());

        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;

        while((line = br.readLine()) != null){
            sb.append(line).append("\n");
        }

        TourReport tourReport = Jackson.getInstance().ObjectFromJSON(sb.toString(), TourReport.class);

        TourItemDao tourItemDao = DAL.getInstance().getTourItemDao();

        tourItemDao.create(tourReport.getTourItem());

        List<TourItem> tourItemList = tourItemDao.getAll();

        tourItem = tourItemList.remove(tourItemList.size() - 1);

        TourLogDao tourLogDao = DAL.getInstance().getTourLogDao();

        tourReport.getTourLogList().forEach(tourLog -> {
            tourLog.setTourid(tourItem.getId());
            tourLogDao.create(tourLog);
        });

        tourOverviewViewModel.getObservableTourItems().clear();
        tourOverviewViewModel.getObservableTourItems().addAll(DAL.getInstance().getTourItemDao().getAll());
    }

    public void exportFile() throws IOException {
        if(this.tourItem == null)
            return;

        List<TourLog> tourLogList = DAL.getInstance().getTourLogDao().getAllById(tourItem.getId());

        TourReport tourReport = new TourReport(tourItem, tourLogList);

        String json = Jackson.getInstance().JSONFromObject(tourReport, true);

        DirectoryChooser dr = new DirectoryChooser();
        dr.setTitle(rm.load("FILE_EXPORT_TITLE"));
        File dir = dr.showDialog(new Stage());

        File file = new File(dir.getAbsolutePath() + File.separator + tourItem.getName() + ".json");

        FileWriter fw = new FileWriter(file);
        fw.write(json);
        fw.close();
    }
}