package org.group07.tourplanner.bl;

import com.lowagie.text.DocumentException;
import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.dal.model.TourLog;
import org.group07.tourplanner.dal.model.TourReport;
import org.group07.tourplanner.dal.model.TourSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PdfGeneratorTest {

    PdfGenerator pdfGenerator;

    @BeforeEach
    void setup(){
        this.pdfGenerator = new PdfGenerator();
    }

    @Test
    void parseSummarizedTemplate() {

        String expectedOutput = "<html>\n" +
                "    <body>\n" +
                "        <h3 style=\"text-align: center; color: green\">\n" +
                "            <span>Summarized Report:</span>\n" +
                "        </h3>\n" +
                "        \n" +
                "        <div>\n" +
                "            <h5>Tour Name: Tour1</h5>\n" +
                "            <span>Average Time: 100.0</span>\n" +
                "            <span>Average Difficulty: 5.0</span>\n" +
                "            <span>Average Rating: 5.0</span>\n" +
                "        </div>\n" +
                "        <div>\n" +
                "            <h5>Tour Name: Tour2</h5>\n" +
                "            <span>Average Time: 50.0</span>\n" +
                "            <span>Average Difficulty: 8.0</span>\n" +
                "            <span>Average Rating: 4.0</span>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>";

        List<TourSummary> tourSummaryList = new ArrayList<>();
        tourSummaryList.add(new TourSummary("Tour1", 100, 5, 5));
        tourSummaryList.add(new TourSummary("Tour2", 50, 8, 4));

        String output = pdfGenerator.parseSummarizedTemplate(tourSummaryList);

        assertEquals(expectedOutput, output);
    }

    /*@Test
    void parseTourTemplate() throws IOException {

        String expectedOutput = "<html>\n" +
                "    <body>\n" +
                "        <h3 style=\"text-align: center; color: green\">\n" +
                "            <span>Tour Report: Tour</span>\n" +
                "        </h3>\n" +
                "        <img src=\"BL/src/main/resources/org/group07/tourplanner/bl/temp.jpg\"/>\n" +
                "\n" +
                "        <p>Tour Description: Description</p>\n" +
                "        <p>From: From</p>\n" +
                "        <p>To: To</p>\n" +
                "        <p>Distance: 1.0</p>\n" +
                "        <p>Estimate: 00:15:00</p>\n" +
                "        <p>Transport Type: PEDESTRIAN</p>\n" +
                "\n" +
                "        <h3>Tour Logs</h3>\n" +
                "        \n" +
                "        <div>\n" +
                "            <h5>Comment: Comment1</h5>\n" +
                "            <span>Date: 2022-01-27</span>\n" +
                "            <span>Difficulty: 5</span>\n" +
                "            <span>Duration: 15</span>\n" +
                "            <span>Rating: 5</span>\n" +
                "        </div>\n" +
                "        <div>\n" +
                "            <h5>Comment: Comment2</h5>\n" +
                "            <span>Date: 2022-05-27</span>\n" +
                "            <span>Difficulty: 4</span>\n" +
                "            <span>Duration: 14</span>\n" +
                "            <span>Rating: 7</span>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>";

        TourItem tourItem = new TourItem(0, "Tour", "Description", "From", "To", "PEDESTRIAN", 1.0, "00:15:00");
        List<TourLog> tourLogList = new ArrayList<>();
        tourLogList.add(new TourLog(0, LocalDate.of(2022, 1, 27), "Comment1", 5, 15, 5));
        tourLogList.add(new TourLog(0, LocalDate.of(2022, 5, 27), "Comment2", 4, 14, 7));

        TourReport tourReport = new TourReport(tourItem, tourLogList);

        String output = "";

        output = pdfGenerator.parseTourTemplate(tourReport);

        assertEquals(expectedOutput, output);
    }*/

    @Test
    void generatePdfFromHtml() throws DocumentException, IOException {

        String pdfFile = "<html>\n" +
                "    <body>\n" +
                "        <h3 style=\"text-align: center; color: green\">\n" +
                "            <span>Summarized Report:</span>\n" +
                "        </h3>\n" +
                "        \n" +
                "        <div>\n" +
                "            <h5>Tour Name: Tour1</h5>\n" +
                "            <span>Average Time: 100.0</span>\n" +
                "            <span>Average Difficulty: 5.0</span>\n" +
                "            <span>Average Rating: 5.0</span>\n" +
                "        </div>\n" +
                "        <div>\n" +
                "            <h5>Tour Name: Tour2</h5>\n" +
                "            <span>Average Time: 50.0</span>\n" +
                "            <span>Average Difficulty: 8.0</span>\n" +
                "            <span>Average Rating: 4.0</span>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>";

        pdfGenerator.generatePdfFromHtml(pdfFile, "./src/test/temp/test.pdf");

        assertTrue(Files.exists(Path.of("./src/test/temp/test.pdf")));

        Files.deleteIfExists(Path.of("./src/test/temp/test.pdf"));
    }
}