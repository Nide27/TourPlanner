package org.group07.tourplanner.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.bl.ResourceManager;
import org.group07.tourplanner.app.viewmodel.EditTourLogViewModel;
import org.group07.tourplanner.dal.DAL;

import java.sql.SQLException;

public class EditTourLogController {

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

    private final EditTourLogViewModel editTourLogViewModel;

    private final ResourceManager rm;

    private static final Logger logger = LogManager.getLogger(DAL.class);

    @FXML
    void initialize() {
        datePicker.valueProperty().bindBidirectional(editTourLogViewModel.getDatePicker());
        commentField.textProperty().bindBidirectional(editTourLogViewModel.getCommentField());
        difficultyField.textProperty().bindBidirectional(editTourLogViewModel.getDifficultyField());
        durationHourField.textProperty().bindBidirectional(editTourLogViewModel.getDurationHourField());
        durationMinuteField.textProperty().bindBidirectional(editTourLogViewModel.getDurationMinuteField());
        ratingField.textProperty().bindBidirectional(editTourLogViewModel.getRatingField());

        datePicker.setDisable(true);
        datePicker.setOpacity(1);
    }

    public EditTourLogController(EditTourLogViewModel editTourLogViewModel) {
        this.editTourLogViewModel = editTourLogViewModel;

        this.rm = ResourceManager.getInstance();
    }

    @FXML
    private void editTourLog(ActionEvent actionEvent){
        if(datePicker.getValue() == null){
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ERROR_FORM"), rm.load("ERROR_DATE"));
            return;
        }
        if(commentField.getText() == null || commentField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ERROR_FORM"), rm.load("ERROR_COMMENT"));
            return;
        }
        if(difficultyField.getText() == null  || difficultyField.getText().isEmpty() || notNumeric(difficultyField.getText()) ||  Integer.parseInt(difficultyField.getText()) < 0 || Integer.parseInt(difficultyField.getText()) > 10){
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ERROR_FORM"), rm.load("ERROR_DIFFICULTY"));
            return;
        }
        if((durationHourField.getText() == null && durationMinuteField.getText() == null) || (durationHourField.getText().isEmpty() && durationMinuteField.getText().isEmpty()) || notNumeric(durationHourField.getText()) || notNumeric(durationMinuteField.getText())){
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ERROR_FORM"), rm.load("ERROR_DURATION"));
            return;
        }
        if(ratingField.getText() == null || ratingField.getText().isEmpty() || notNumeric(ratingField.getText()) ||  Integer.parseInt(ratingField.getText()) < 0 || Integer.parseInt(ratingField.getText()) > 10){
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ERROR_FORM"), rm.load("ERROR_RATING"));
            return;
        }

        try {
            this.editTourLogViewModel.editTourLog();
        } catch (SQLException e) {
            logger.error("DB error:\n" + e);
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
