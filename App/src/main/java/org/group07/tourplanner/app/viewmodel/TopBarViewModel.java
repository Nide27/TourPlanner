package org.group07.tourplanner.app.viewmodel;

import org.group07.tourplanner.bl.PdfGenerator;
import org.group07.tourplanner.dal.DAL;
import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.dal.model.TourLog;
import org.group07.tourplanner.dal.model.TourReport;
import org.group07.tourplanner.dal.model.TourSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TopBarViewModel {

    private static TourItem tourItem;

    public TopBarViewModel(){}

    public void setTourModel(TourItem tourItem){

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

        PdfGenerator pdfGenerator = new PdfGenerator();
        String mypdf = pdfGenerator.parseSummarizedTemplate(tourSummaryList);
        pdfGenerator.generatePdfFromHtml(mypdf);
    }

    public void createTourReport(){

        if(this.tourItem == null)
            return;

        List<TourLog> tourLogList = DAL.getInstance().getTourLogDao().getAllById(this.tourItem.getId());

        PdfGenerator pdfGenerator = new PdfGenerator();
        String html = pdfGenerator.parseTourTemplate(new TourReport(this.tourItem, tourLogList));
        pdfGenerator.generatePdfFromHtml(html);
    }

}
