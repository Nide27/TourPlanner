package org.group07.tourplanner.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.app.viewmodel.CreateTourItemViewModel;
import org.group07.tourplanner.dal.ConfigManager;

import java.util.ResourceBundle;

public class CreateTourItemController {
    @FXML
    private ComboBox<String> transportTyp;
    @FXML
    private Button submitButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField fromField;
    @FXML
    private TextField toField;

    private final CreateTourItemViewModel createTourItemViewModel;

    public CreateTourItemController(CreateTourItemViewModel createTourItemViewModel) {
        this.createTourItemViewModel = createTourItemViewModel;
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
        ResourceBundle res = ResourceBundle.getBundle("org.group07.tourplanner.app." + "gui_strings", ConfigManager.getInstance().getLocale());
        Window owner = submitButton.getScene().getWindow();
        if(nameField.getText() == null || nameField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, res.getString("ERROR_FORM"), res.getString("ERROR_NAME"));
            return;
        }
        if(descriptionField.getText() == null || descriptionField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, res.getString("ERROR_FORM"), res.getString("ERROR_DESCRIPTION"));
            return;
        }
        if(fromField.getText() == null || fromField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, res.getString("ERROR_FORM"), res.getString("ERROR_FROM"));
            return;
        }
        if(toField.getText() == null || toField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, res.getString("ERROR_FORM"), res.getString("ERROR_TO"));
            return;
        }
        if(transportTyp.getValue() == null || transportTyp.getValue().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, res.getString("ERROR_FORM"), res.getString("ERROR_TRANSPORT"));
            return;
        }

        this.createTourItemViewModel.createTour();
    }
}
