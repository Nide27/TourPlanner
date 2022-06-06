package org.group07.tourplanner.app.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.bl.ResourceManager;
import org.group07.tourplanner.app.viewmodel.CreateTourLogViewModel;
import org.group07.tourplanner.dal.logger.LogManager;

import java.sql.SQLException;

public class CreateTourLogController {

    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField commentField;
    @FXML
    private TextField difficultyField;
    @FXML
    private TextField durationHourField;
    @FXML
    private TextField durationMinuteField;
    @FXML
    private TextField ratingField;

    private final CreateTourLogViewModel createTourLogViewModel;
    
    private final ResourceManager rm;

    @FXML
    void initialize() {
        datePicker.valueProperty().bindBidirectional(createTourLogViewModel.getDatePicker());
        commentField.textProperty().bindBidirectional(createTourLogViewModel.getCommentField());
        difficultyField.textProperty().bindBidirectional(createTourLogViewModel.getDifficultyField());
        durationHourField.textProperty().bindBidirectional(createTourLogViewModel.getDurationHourField());
        durationMinuteField.textProperty().bindBidirectional(createTourLogViewModel.getDurationMinuteField());
        ratingField.textProperty().bindBidirectional(createTourLogViewModel.getRatingField());
    }

    public CreateTourLogController(CreateTourLogViewModel createTourLogViewModel) {
        this.createTourLogViewModel = createTourLogViewModel;
        this.rm = ResourceManager.getInstance();
    }

    @FXML
    private void addTourLog(ActionEvent actionEvent){

        if(datePicker.getValue() == null){
            LogManager.getLogger().debug("Inavlid input in datePicker");
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ERROR_FORM"), rm.load("ERROR_DATE"));
            return;
        }
        if(commentField.getText() == null || commentField.getText().isEmpty()){
            LogManager.getLogger().debug("Inavlid input in commentField");
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ERROR_FORM"), rm.load("ERROR_COMMENT"));
            return;
        }
        if(difficultyField.getText() == null  || difficultyField.getText().isEmpty() || notNumeric(difficultyField.getText()) ||  Integer.parseInt(difficultyField.getText()) < 0 || Integer.parseInt(difficultyField.getText()) > 10){
            LogManager.getLogger().debug("Inavlid input in difficultyField");
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ERROR_FORM"), rm.load("ERROR_DIFFICULTY"));
            return;
        }
        if((durationHourField.getText() == null && durationMinuteField.getText() == null) || (durationHourField.getText().isEmpty() && durationMinuteField.getText().isEmpty()) || notNumeric(durationHourField.getText()) || notNumeric(durationMinuteField.getText())){
            LogManager.getLogger().debug("Inavlid input in durationHourField");
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ERROR_FORM"), rm.load("ERROR_DURATION"));
            return;
        }
        if(ratingField.getText() == null || ratingField.getText().isEmpty() || notNumeric(ratingField.getText()) ||  Integer.parseInt(ratingField.getText()) < 0 || Integer.parseInt(ratingField.getText()) > 10){
            LogManager.getLogger().warn("Inavlid input in RatingField");
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ERROR_FORM"), rm.load("ERROR_RATING"));
            return;
        }

        try {
            this.createTourLogViewModel.createTourLog();
        } catch (SQLException e) {
            LogManager.getLogger().fatal("DB error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_DB"));
        }
    }

    private boolean notNumeric(String strNum) {
        if (strNum == null) {
            return true;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }
}
