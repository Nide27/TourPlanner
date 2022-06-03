package org.group07.tourplanner.app.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.bl.ResourceManager;
import org.group07.tourplanner.app.viewmodel.CreateTourItemViewModel;

import java.sql.SQLException;

public class CreateTourItemController {
    @FXML
    private ComboBox<String> transportTyp;
    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField fromField;
    @FXML
    private TextField toField;

    private final CreateTourItemViewModel createTourItemViewModel;

    private final ResourceManager rm;

    private static final Logger logger = LogManager.getLogger(CreateTourItemController.class);

    public CreateTourItemController(CreateTourItemViewModel createTourItemViewModel) {
        this.createTourItemViewModel = createTourItemViewModel;
        this.rm = ResourceManager.getInstance();
    }

    @FXML
    void initialize() {
        transportTyp.getItems().setAll("Cycling", "Driving", "Walking");
        nameField.textProperty().bindBidirectional(createTourItemViewModel.getName());
        descriptionField.textProperty().bindBidirectional(createTourItemViewModel.getDescription());
        fromField.textProperty().bindBidirectional(createTourItemViewModel.getFrom());
        toField.textProperty().bindBidirectional(createTourItemViewModel.getTo());
        transportTyp.valueProperty().bindBidirectional(createTourItemViewModel.getTransportType());
    }

    @FXML
    private void addTour(ActionEvent actionEvent) {
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
            this.createTourItemViewModel.createTour();
        } catch (SQLException e) {
            logger.error("DB error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_DB"));
        }
    }
}
