package org.group07.tourplanner.bl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import org.group07.tourplanner.dal.Jackson;
import org.group07.tourplanner.dal.model.TourItem;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MapQuestThread extends Thread {

    private final String key = "Zo9e7MdMG26Xb55t0Fusnyo75Fage2ib";

    private String requestRoute;
    private String requestMap;

    @Getter
    private TourItem tourItem;

    private HttpClient client;

    public MapQuestThread(TourItem tourItem){
        this.tourItem = tourItem;

        client = HttpClient.newHttpClient();

        requestRoute = "http://www.mapquestapi.com/directions/v2/route?key="
                        + key
                        + "&from="
                        + tourItem.getDeparture()
                        + "&to="
                        + tourItem.getDestination()
                        + "&routeType="
                        + tourItem.getTransport();
    }

    @Override
    public void run() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(requestRoute))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            ObjectNode node = Jackson.getInstance().getObjMapper().readValue(response.body(), ObjectNode.class);

            tourItem.setDistance(node.get("distance").asDouble());
            tourItem.setEstimate(node.get("formattedTime").asText());

            String sessionID = node.get("sessionId").asText();

            requestMap = "https://www.mapquestapi.com/staticmap/v5/map?"
                        + "key="
                        + key
                        + "session="
                        + sessionID
                        + "&size=500,500@2x";

            request = HttpRequest.newBuilder()
                    .uri(URI.create(requestMap))
                    .header("Content-Type", "image/jpeg")
                    .GET()
                    .build();

            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
