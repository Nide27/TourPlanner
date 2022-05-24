package org.group07.tourplanner.bl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.SneakyThrows;
import org.group07.tourplanner.dal.ConfigManager;
import org.group07.tourplanner.dal.Jackson;
import org.group07.tourplanner.dal.config.MapQuestConfig;
import org.group07.tourplanner.dal.model.TourItem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

public class MapQuestThread extends Thread {

    private final String key;
    private final String keyConfigPath = "/Users/edinmuhovic/Documents/FH 4.Sem/SWE2/TourPlanner/DAL/src/main/resources/org/group07/tourplanner/dal/mapquestconfig.json";

    private String requestRoute;
    private String requestMap;

    @Getter
    private TourItem tourItem;

    private HttpClient client;

    private ObjectProperty<Image> imgProperty;
    private StringProperty distance;
    private StringProperty estimate;

    @SneakyThrows
    public MapQuestThread(TourItem tourItem, ObjectProperty<Image> imgProperty, StringProperty distance, StringProperty estimate){
        this.tourItem = tourItem;

        this.key = ConfigManager.getInstance().loadConfigFromFile(keyConfigPath, MapQuestConfig.class).getKey();

        client = HttpClient.newHttpClient();

        requestRoute = "http://www.mapquestapi.com/directions/v2/route?key="
                        + key
                        + "&from="
                        + tourItem.getDeparture().replace(" ", "-")
                        + "&to="
                        + tourItem.getDestination().replace(" ", "-")
                        + "&routeType="
                        + tourItem.getTransport();

        this.distance = distance;
        this.estimate = estimate;
        this.imgProperty = imgProperty;
        InputStream stream = new FileInputStream("/Users/edinmuhovic/Documents/FH 4.Sem/SWE2/TourPlanner/App/src/main/resources/activity_indicator.gif");
        Image image =  new Image(stream, 200, 200, false, false);

        imgProperty.set(image);
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

            if(response.statusCode() != 200) {
                InputStream stream = new FileInputStream("/Users/edinmuhovic/Documents/FH 4.Sem/SWE2/TourPlanner/App/src/main/resources/no-image-icon-15.png");
                Image image = new Image(stream, 200, 200, false, false);
                imgProperty.set(image);
                return;
            }

            JsonNode node = Jackson.getInstance().getObjMapper().readValue(response.body(), ObjectNode.class).get("route");

            if(node.get("distance") == null || node.get("formattedTime") == null || node.get("sessionId") == null){
                InputStream stream = new FileInputStream("/Users/edinmuhovic/Documents/FH 4.Sem/SWE2/TourPlanner/App/src/main/resources/no-image-icon-15.png");
                Image image = new Image(stream, 200, 200, false, false);
                imgProperty.set(image);
                return;
            }

            ResourceBundle res = ResourceBundle.getBundle("org.group07.tourplanner.app." + "gui_strings", ConfigManager.getInstance().getLocale());

            Double dist = node.get("distance").asDouble();
            String est = node.get("formattedTime").asText();

            tourItem.setDistance(dist);
            tourItem.setEstimate(est);

            distance.setValue(res.getString("DETAIL_DISTANCE") + dist + " km");
            estimate.setValue(res.getString("DETAIL_ESTIMATE") + est);

            String sessionID = node.get("sessionId").asText();

            requestMap = "https://www.mapquestapi.com/staticmap/v5/map?"
                        + "key="
                        + key
                        + "&session="
                        + sessionID
                        + "&size=380,170@2x";

            BufferedImage bufImg = ImageIO.read(new URL(requestMap));
            ImageIO.write(bufImg, "jpg", new File("BL/src/main/resources/org/group07/tourplanner/bl/temp.jpg"));
            Image img = SwingFXUtils.toFXImage(bufImg, null);
            imgProperty.set(img);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
