package org.group07.tourplanner.app.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.SneakyThrows;
import org.group07.tourplanner.app.FXMLDependencyInjection;
import org.group07.tourplanner.dal.DAL;
import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.dal.model.TourLog;

import java.time.LocalDate;
import java.util.Locale;


public class CreateTourLogViewModel {

    private Stage newStage;
    private ObservableList<TourLog> tourLogsList;

    private int tourid;

    @Getter
    private final ObjectProperty<LocalDate> datePicker = new SimpleObjectProperty<>();
    @Getter
    private final StringProperty commentField = new SimpleStringProperty();
    @Getter
    private final StringProperty difficultyField = new SimpleStringProperty();
    @Getter
    private final StringProperty durationHourField = new SimpleStringProperty();
    @Getter
    private final StringProperty durationMinuteField = new SimpleStringProperty();
    @Getter
    private final StringProperty ratingField = new SimpleStringProperty();

    @SneakyThrows
    public void createWindow(ObservableList<TourLog> list, TourItem tourItem){
        this.tourLogsList = list;
        this.tourid = tourItem.getId();

        Parent root = FXMLDependencyInjection.load("CreateTourLog.fxml", Locale.ENGLISH);

        Scene newScene = new Scene(root);

        newStage = new Stage();
        newStage.setTitle("Create TourLog");
        newStage.setScene(newScene);

        newStage.showAndWait();
    }

    public void createTourLog(){
        int difficulty = Integer.parseInt(difficultyField.get());
        int duration = Integer.parseInt(durationHourField.get()) * 60 + Integer.parseInt(durationMinuteField.get());
        int rating = Integer.parseInt(ratingField.get());

        TourLog tourLog = new TourLog(tourid, datePicker.getValue(), commentField.get(), difficulty, duration, rating);

        DAL.getInstance().getTourLogDao().create(tourLog);

        tourLogsList.clear();
        tourLogsList.addAll(DAL.getInstance().getTourLogDao().getAllById(tourid));
        newStage.close();
    }
}
