package org.group07.tourplanner.app.viewmodel;

import java.time.LocalDate;

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
import org.group07.tourplanner.dal.model.TourLog;

public class EditTourLogViewModel {

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

    private Stage stage;
    private ObservableList<TourLog> tourLogsList;

    private int tourid;

    private final ResourceManager rm;

    public EditTourLogViewModel(){
        this.rm = ResourceManager.getInstance();
    }

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

        Parent root = FXMLDependencyInjection.load("EditTourLog.fxml", ConfigManager.getInstance().getLocale());

        Scene scene = new Scene(root);

        stage = new Stage();
        stage.setTitle("Edit TourLog");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();
    }

    public void editTourLog(){
        int difficulty = Integer.parseInt(difficultyField.get());
        int duration = Integer.parseInt(durationHourField.get()) * 60 + Integer.parseInt(durationMinuteField.get());
        int rating = Integer.parseInt(ratingField.get());

        TourLog tourLog = new TourLog(tourid, datePicker.getValue(), commentField.get(), difficulty, duration, rating);

        DAL.getInstance().getTourLogDao().update(tourLog);

        tourLogsList.clear();
        tourLogsList.addAll(DAL.getInstance().getTourLogDao().getAllById(tourid));

        stage.close();

        AlertHelper.showAlert(Alert.AlertType.INFORMATION, rm.load("INFORMATION_SUCCESS"), rm.load("INFORMATION_LOG_UPDATED"));
    }
}
