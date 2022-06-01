package org.group07.tourplanner.bl;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import lombok.SneakyThrows;

import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.dal.model.TourReport;
import org.group07.tourplanner.dal.model.TourSummary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class PdfGenerator {

    public String parseSummarizedTemplate(List<TourSummary> list) {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("title", "Summarized Report:");

        context.setVariable("list", list);

        return templateEngine.process("org/group07/tourplanner/bl/pdfsummarizedtemplate", context);
    }

    public String parseTourTemplate(TourReport report) {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TourItem tourItem = report.getTourItem();
        StringProperty distance = new SimpleStringProperty();
        StringProperty estimate = new SimpleStringProperty();

        distance.setValue(String.valueOf(tourItem.getDistance()));
        estimate.setValue(tourItem.getEstimate());

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("title", "Tour Report:");

        ObjectProperty<javafx.scene.image.Image> imageView = new SimpleObjectProperty<>();


        //MAPQUEST API THREAD
        MapQuestThread mapQuestThread = new MapQuestThread(tourItem, imageView, distance, estimate);
        mapQuestThread.start();

        String link = "BL/src/main/resources/org/group07/tourplanner/bl/temp.jpg";

        context.setVariable("logList", report.getTourLogList());
        context.setVariable("tourData", report.getTourItem());
        context.setVariable("img", link);


        return templateEngine.process("org/group07/tourplanner/bl/pdftourtemplate", context);
    }

    @SneakyThrows
    public void generatePdfFromHtml(String html, String path) {
        OutputStream outputStream = new FileOutputStream(path);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }

}
