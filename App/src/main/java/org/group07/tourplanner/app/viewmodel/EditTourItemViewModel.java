package org.group07.tourplanner.app.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Getter;
import lombok.SneakyThrows;
import org.group07.tourplanner.app.FXMLDependencyInjection;
import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.dal.ConfigManager;
import org.group07.tourplanner.dal.DAL;
import org.group07.tourplanner.dal.model.TourItem;

import java.util.Locale;
import java.util.ResourceBundle;

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
    private final StringProperty transport = new SimpleStringProperty();

    private Stage newWindow = new Stage();
    private Window main = null;
    private ObservableList<TourItem> tourList;
    private int id;

    @SneakyThrows
    public void createWindow(ObservableList<TourItem> list, TourItem tourItem){

        id = tourItem.getId();
        name.set(tourItem.getName());
        description.set(tourItem.getDescription());
        from.set(tourItem.getDeparture());
        to.set(tourItem.getDestination());
        transport.set(tourItem.getTransport());

        this.tourList = list;
        Locale currentLocale = Locale.getDefault();
        String country = System.getProperty("user.country");
        System.out.println(country);
        //ResourceBundle res = ResourceBundle.getBundle("org.group07.tourplanner.app." + "gui_strings", Locale.getDefault());

        Parent root = FXMLDependencyInjection.load("EditTourItem.fxml", ConfigManager.getInstance().getLocale());

        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/group07/tourplanner/app/CreateTourItem.fxml")));
        //Label secondLabel = new Label("I'm a Label on new Window");

        //StackPane secondaryLayout = new StackPane(root);
        //secondaryLayout.getChildren().add(secondLabel);

        Scene secondScene = new Scene(root);

        // New window (Stage)
        if (newWindow != null){
            newWindow.close();
        }

        newWindow.setTitle("Edit Tour");
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.APPLICATION_MODAL);
/*
        newWindow.initModality(Modality.WINDOW_MODAL);

        newWindow.initOwner(main);*/

        newWindow.show();
    }

    public void editTour(){
        //API AUFRUF
        TourItem tourItem = new TourItem(id, name.get(), description.get(), from.get(), to.get(), transport.get(), 0, 0);
        DAL.getInstance().getTourItemDao().update(tourItem);
        tourList.clear();
        tourList.addAll(DAL.getInstance().getTourItemDao().getAll());
        newWindow.close();
        ResourceBundle res = ResourceBundle.getBundle("org.group07.tourplanner.app." + "gui_strings", Locale.ENGLISH);
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, main, res.getString("INFORMATION_SUCCESS"), res.getString("INFORMATION_UPDATED"));
    }
}
