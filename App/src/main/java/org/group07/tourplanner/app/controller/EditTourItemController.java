package org.group07.tourplanner.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.bl.ResourceManager;
import org.group07.tourplanner.app.viewmodel.EditTourItemViewModel;

import java.sql.SQLException;

public class EditTourItemController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField fromField;
    @FXML
    private TextField toField;
    @FXML
    private ComboBox<String> transportTyp;

    private final EditTourItemViewModel editTourItemViewModel;

    private final ResourceManager rm;

    private static final Logger logger = LogManager.getLogger(EditTourItemController.class);

    public EditTourItemController(EditTourItemViewModel editTourItemViewModel){
        this.editTourItemViewModel = editTourItemViewModel;
        this.rm = ResourceManager.getInstance();
    }

    @FXML
    void initialize() {
        transportTyp.getItems().setAll("Cycling", "Driving", "Walking");
        nameField.textProperty().bindBidirectional(editTourItemViewModel.getName());
        descriptionField.textProperty().bindBidirectional(editTourItemViewModel.getDescription());
        fromField.textProperty().bindBidirectional(editTourItemViewModel.getFrom());
        toField.textProperty().bindBidirectional(editTourItemViewModel.getTo());
        transportTyp.valueProperty().bindBidirectional(editTourItemViewModel.getTransportType());
    }

    @FXML
    private void editTour(ActionEvent actionEvent){
        if(nameField.getText() == null || nameField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ERROR_FORM"), rm.load("ERROR_NAME"));
            return;
        }
        if(descriptionField.getText() == null || descriptionField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ERROR_FORM"), rm.load("ERROR_DESCRIPTION"));
            return;
        }
        if(fromField.getText() == null || fromField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ERROR_FORM"), rm.load("ERROR_FROM"));
            return;
        }
        if(toField.getText() == null || toField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ERROR_FORM"), rm.load("ERROR_TO"));
            return;
        }
        if(transportTyp.getValue() == null || transportTyp.getValue().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ERROR_FORM"), rm.load("ERROR_TRANSPORT"));
            return;
        }

        try {
            this.editTourItemViewModel.editTour();
        } catch (SQLException e) {
            logger.error("DB error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_DB"));
        }
    }
}
