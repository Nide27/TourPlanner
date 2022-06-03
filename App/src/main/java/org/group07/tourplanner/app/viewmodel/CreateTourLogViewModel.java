package org.group07.tourplanner.app.viewmodel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

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
import org.group07.tourplanner.dal.ConfigManager;
import org.group07.tourplanner.dal.DAL;
import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.dal.model.TourLog;

public class CreateTourLogViewModel {

    private Stage stage;
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

    public void createWindow(ObservableList<TourLog> list, TourItem tourItem) throws IOException {
        this.tourLogsList = list;
        this.tourid = tourItem.getId();

        Parent root = FXMLDependencyInjection.load("CreateTourLog.fxml", ConfigManager.getInstance().getLocale());

        Scene scene = new Scene(root);

        stage = new Stage();
        stage.setTitle("Create TourLog");
        stage.setScene(scene);

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();
    }

    public void createTourLog() throws SQLException {
        int difficulty = Integer.parseInt(difficultyField.get());
        int duration = Integer.parseInt(durationHourField.get()) * 60 + Integer.parseInt(durationMinuteField.get());
        int rating = Integer.parseInt(ratingField.get());

        TourLog tourLog = new TourLog(tourid, datePicker.getValue(), commentField.get(), difficulty, duration, rating);

        DAL.getInstance().getTourLogDao().create(tourLog);

        tourLogsList.clear();
        tourLogsList.addAll(DAL.getInstance().getTourLogDao().getAllById(tourid));

        datePicker.setValue(null);
        commentField.setValue("");
        difficultyField.setValue("");
        durationHourField.setValue("");
        durationMinuteField.setValue("");
        ratingField.setValue("");

        stage.close();

        ResourceBundle res = ResourceBundle.getBundle("org.group07.tourplanner.app." + "gui_strings", Locale.ENGLISH);
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, res.getString("INFORMATION_SUCCESS"), res.getString("INFORMATION_LOG_CREATED"));
    }
}
