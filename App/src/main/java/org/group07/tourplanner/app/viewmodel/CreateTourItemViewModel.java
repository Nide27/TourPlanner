package org.group07.tourplanner.app.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import lombok.Getter;

import org.group07.tourplanner.app.FXMLDependencyInjection;
import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.bl.ResourceManager;
import org.group07.tourplanner.dal.ConfigManager;
import org.group07.tourplanner.dal.DAL;
import org.group07.tourplanner.dal.model.TourItem;

import java.io.IOException;
import java.sql.SQLException;

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
    private final ObjectProperty<String> transportType = new SimpleObjectProperty<>();

    private final ResourceManager rm;

    private Stage stage;
    private ObservableList<TourItem> tourList;

    public CreateTourItemViewModel(){
        this.rm = ResourceManager.getInstance();
    }

    public void createWindow(ObservableList<TourItem> list) throws IOException {

        this.tourList = list;
        Parent root = FXMLDependencyInjection.load("CreateTourItem.fxml", ConfigManager.getInstance().getLocale());

        Scene scene = new Scene(root);

        stage = new Stage();
        stage.setTitle("Create Tour");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void createTour() throws SQLException {

        String transport;

        switch (transportType.get()){
            case "Cycling":
                transport = "bicycle"; break;
            case "Driving":
                transport = "fastest"; break;
            case "Walking":
                transport = "pedestrian"; break;
            default: transport = "";
        }

        TourItem tourItem = new TourItem(0, name.get(), description.get(), from.get(), to.get(), transport, 0, "00:00:00");
        DAL.getInstance().getTourItemDao().create(tourItem);

        tourList.clear();
        tourList.addAll(DAL.getInstance().getTourItemDao().getAll());

        name.setValue("");
        description.setValue("");
        from.setValue("");
        to.setValue("");
        transportType.setValue("");

        stage.close();

        AlertHelper.showAlert(Alert.AlertType.INFORMATION, rm.load("INFORMATION_SUCCESS"), rm.load("INFORMATION_TOUR_CREATED"));
    }
}
