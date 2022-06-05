package org.group07.tourplanner.dal;

import org.group07.tourplanner.dal.model.TourItem;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class JacksonTest {

    @Test
    void getInstance() {
        Object obj = Jackson.getInstance();

        assertNotNull(obj);
    }

    @Test
    void objectFromJSON() throws IOException {
        FileReader fr = new FileReader("./src/test/resources/org/group07/tourplanner/dal/testtouritem.json");

        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(fr);

        String line;

        while((line = br.readLine()) != null){
            sb.append(line).append("\n");
        }

        TourItem tourItem = Jackson.getInstance().ObjectFromJSON(sb.toString(), TourItem.class);

        assertEquals(0, tourItem.getId());
        assertEquals("Test", tourItem.getName());
        assertEquals("Description", tourItem.getDescription());
        assertEquals("Wien", tourItem.getDeparture());
        assertEquals("Linz", tourItem.getDestination());
        assertEquals("FASTEST", tourItem.getTransport());
        assertEquals(100, tourItem.getDistance());
        assertEquals("01:45:00", tourItem.getEstimate());
    }

    @Test
    void JSONFromObject() throws IOException {
        String expectedOutput = "{\n" +
                "  \"id\" : 0,\n" +
                "  \"name\" : \"Tour\",\n" +
                "  \"description\" : \"Description\",\n" +
                "  \"departure\" : \"From\",\n" +
                "  \"destination\" : \"To\",\n" +
                "  \"transport\" : \"PEDESTRIAN\",\n" +
                "  \"distance\" : 1.0,\n" +
                "  \"estimate\" : \"00:15:00\"\n" +
                "}";

        TourItem tourItem = new TourItem(0, "Tour", "Description", "From", "To", "PEDESTRIAN", 1.0, "00:15:00");

        String output = Jackson.getInstance().JSONFromObject(tourItem, true);

        assertEquals(expectedOutput, output);
    }
}