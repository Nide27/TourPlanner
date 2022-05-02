package org.group07.tourplanner.app.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Getter;
import lombok.SneakyThrows;
import org.group07.tourplanner.app.FXMLDependencyInjection;
import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.dal.DAL;
import org.group07.tourplanner.dal.model.TourItem;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreateTourViewModel {


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

    @SneakyThrows
    public void createWindow(Button button, ObservableList<TourItem> list){

        this.tourList = list;
        this.main = button.getScene().getWindow();
        Locale currentLocale = Locale.getDefault();
        String country = System.getProperty("user.country");
        System.out.println(country);
        //ResourceBundle res = ResourceBundle.getBundle("org.group07.tourplanner.app." + "gui_strings", Locale.getDefault());

        Parent root = FXMLDependencyInjection.load("CreateTour.fxml", Locale.ENGLISH);

        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/group07/tourplanner/app/CreateTour.fxml")));
        //Label secondLabel = new Label("I'm a Label on new Window");

        //StackPane secondaryLayout = new StackPane(root);
        //secondaryLayout.getChildren().add(secondLabel);

        Scene secondScene = new Scene(root);

        // New window (Stage)

        newWindow.setTitle("Create Tour");
        newWindow.setScene(secondScene);

        newWindow.initModality(Modality.WINDOW_MODAL);

        newWindow.initOwner(main);

        newWindow.show();
    }

    public void createTour(){
        //API AUFRUF
        TourItem tourItem = new TourItem(0, name.get(), description.get(), from.get(), to.get(), transport.get(), 0, 0);
        DAL.getInstance().getTourItemDao().create(tourItem);
        tourList.clear();
        tourList.addAll(DAL.getInstance().getTourItemDao().getAll());
        newWindow.close();
        ResourceBundle res = ResourceBundle.getBundle("org.group07.tourplanner.app." + "gui_strings", Locale.ENGLISH);
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, main, res.getString("INFORMATION_SUCCESS"), res.getString("INFORMATION_CREATED"));

    }

}
