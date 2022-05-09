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
import org.group07.tourplanner.dal.model.TourLog;

import java.time.LocalDate;
import java.util.Locale;

public class EditTourLogViewModel {

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
    public void createWindow(ObservableList<TourLog> list, TourLog tourLog){

        System.out.println(tourLog.getComment());
        this.tourid = tourLog.getTourid();
        this.datePicker.set(tourLog.getDate());
        this.commentField.set(tourLog.getComment());
        this.difficultyField.set(Integer.toString(tourLog.getDifficulty()));
        this.durationHourField.set(Integer.toString(tourLog.getDuration() / 60));
        this.durationMinuteField.set(Integer.toString(tourLog.getDuration() % 60));
        this.ratingField.set(Integer.toString(tourLog.getRating()));

        this.tourLogsList = list;

        Parent root = FXMLDependencyInjection.load("EditTourLog.fxml", Locale.ENGLISH);

        Scene newScene = new Scene(root);

        newStage = new Stage();
        newStage.setTitle("Edit TourLog");
        newStage.setScene(newScene);

        newStage.showAndWait();
    }

    public void editTourLog(){
        int difficulty = Integer.parseInt(difficultyField.get());
        int duration = Integer.parseInt(durationHourField.get()) * 60 + Integer.parseInt(durationMinuteField.get());
        int rating = Integer.parseInt(ratingField.get());

        TourLog tourLog = new TourLog(tourid, datePicker.getValue(), commentField.get(), difficulty, duration, rating);

        DAL.getInstance().getTourLogDao().update(tourLog);

        tourLogsList.clear();
        tourLogsList.addAll(DAL.getInstance().getTourLogDao().getAllById(tourid));
        newStage.close();
    }
}
