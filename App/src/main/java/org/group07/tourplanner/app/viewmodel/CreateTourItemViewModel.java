package org.group07.tourplanner.app.viewmodel;

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
        TourItem tourItem = new TourItem(0, name.get(), description.get(), from.get(), to.get(), transport.get(), 0, 0);
        DAL.getInstance().getTourItemDao().create(tourItem);
        tourList.clear();
        tourList.addAll(DAL.getInstance().getTourItemDao().getAll());
        newStage.close();
    }

}
