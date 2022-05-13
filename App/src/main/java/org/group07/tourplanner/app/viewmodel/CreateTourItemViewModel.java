package org.group07.tourplanner.app.viewmodel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.SneakyThrows;
import org.group07.tourplanner.app.FXMLDependencyInjection;
import org.group07.tourplanner.dal.ConfigManager;
import org.group07.tourplanner.dal.DAL;
import org.group07.tourplanner.dal.model.TourItem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;

public class CreateTourItemViewModel {

    @Getter
    private final StringProperty name = new SimpleStringProperty();
    @Getter
    private final StringProperty description = new SimpleStringProperty();
    @Getter
    private final StringProperty from = new SimpleStringProperty();
    @Getter
    private final StringProperty to = new SimpleStringProperty();
    @Getter
    private final StringProperty transport = new SimpleStringProperty();

    private Stage newStage;
    private ObservableList<TourItem> tourList;

    @SneakyThrows
    public void createWindow(ObservableList<TourItem> list){

        this.tourList = list;
        Locale currentLocale = Locale.getDefault();
        String country = System.getProperty("user.country");
        System.out.println(country);
        //ResourceBundle res = ResourceBundle.getBundle("org.group07.tourplanner.app." + "gui_strings", Locale.getDefault());

        Parent root = FXMLDependencyInjection.load("CreateTourItem.fxml", ConfigManager.getInstance().getLocale());

        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/group07/tourplanner/app/CreateTourItem.fxml")));
        //Label secondLabel = new Label("I'm a Label on new Window");

        //StackPane secondaryLayout = new StackPane(root);
        //secondaryLayout.getChildren().add(secondLabel);

        Scene newScene = new Scene(root);
        newStage = new Stage();

        // New stage

        newStage.setTitle("Create Tour");
        newStage.setScene(newScene);

        //newWindow.initModality(Modality.WINDOW_MODAL);

        //newWindow.initOwner(main);
        newStage.initModality(Modality.APPLICATION_MODAL);

        newStage.showAndWait();
    }


    public void createTour(){
        HttpClient client;
        ObjectMapper mapper;

        client = HttpClient.newHttpClient();
        mapper = new ObjectMapper();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    //"http://www.mapquestapi.com/directions/v2/route?key=Zo9e7MdMG26Xb55t0Fusnyo75Fage2ib&from=Wien,schwedenplatz&to=Wien,karlsplatz&routeType=bicycle")
                    .uri(URI.create("https://www.mapquestapi.com/staticmap/v5/map?session=627e6459-0048-993a-02b4-3827-12c43d482d1f&size=500,500@2x&key=Zo9e7MdMG26Xb55t0Fusnyo75Fage2ib&routeType=pedestrian"))
                    .header("Content-Type", "image/jpeg")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200){

                //System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(response.body())));
                System.out.println(response.body() + "\n");
            }else if (response.statusCode() == 400){
                System.out.println("No User detected\n");
            }else {
                System.out.println("Something went wrong!\n");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        TourItem tourItem = new TourItem(0, name.get(), description.get(), from.get(), to.get(), transport.get(), 0, "00:00:00");
        DAL.getInstance().getTourItemDao().create(tourItem);
        tourList.clear();
        tourList.addAll(DAL.getInstance().getTourItemDao().getAll());
        newStage.close();
    }

}
