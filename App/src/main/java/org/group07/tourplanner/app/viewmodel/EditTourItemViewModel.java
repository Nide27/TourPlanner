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
import lombok.SneakyThrows;

import org.group07.tourplanner.app.FXMLDependencyInjection;
import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.app.helper.ResourceManager;
import org.group07.tourplanner.dal.ConfigManager;
import org.group07.tourplanner.dal.DAL;
import org.group07.tourplanner.dal.model.TourItem;

public class EditTourItemViewModel {

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

    private Stage stage;
    private ObservableList<TourItem> tourList;
    private int id;

    private final ResourceManager rm;

    public EditTourItemViewModel(){
        this.rm = ResourceManager.getInstance();
    }

    @SneakyThrows
    public void createWindow(ObservableList<TourItem> list, TourItem tourItem){

        id = tourItem.getId();
        name.set(tourItem.getName());
        description.set(tourItem.getDescription());
        from.set(tourItem.getDeparture());
        to.set(tourItem.getDestination());

        switch (tourItem.getTransport()){
            case "bicycle":
                transportType.set("Cycling"); break;
            case "fastest":
                transportType.set("Driving"); break;
            case "pedestrian":
                transportType.set("Walking"); break;
        }

        this.tourList = list;

        Parent root = FXMLDependencyInjection.load("EditTourItem.fxml", ConfigManager.getInstance().getLocale());

        Scene scene = new Scene(root);

        stage = new Stage();
        stage.setTitle("Edit Tour");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void editTour(){

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

        TourItem tourItem = new TourItem(id, name.get(), description.get(), from.get(), to.get(), transport, 0, "00:00:00");
        DAL.getInstance().getTourItemDao().update(tourItem);

        tourList.clear();
        tourList.addAll(DAL.getInstance().getTourItemDao().getAll());

        name.setValue("");
        description.setValue("");
        from.setValue("");
        to.setValue("");
        transportType.setValue("");

        stage.close();

        AlertHelper.showAlert(Alert.AlertType.INFORMATION, rm.load("INFORMATION_SUCCESS"), rm.load("INFORMATION_TOUR_UPDATED"));
    }
}
