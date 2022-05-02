package org.group07.tourplanner.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.app.viewmodel.CreateTourViewModel;

import java.util.Locale;
import java.util.ResourceBundle;

public class CreateTourController {
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
    @FXML
    private TextField transportField;

    private final CreateTourViewModel createTourViewModel;

    public CreateTourController(CreateTourViewModel createTourViewModel) {
        this.createTourViewModel = createTourViewModel;
    }

    @FXML
    void initialize() {
        nameField.textProperty().bindBidirectional(createTourViewModel.getName());
        descriptionField.textProperty().bindBidirectional(createTourViewModel.getDescription());
        fromField.textProperty().bindBidirectional(createTourViewModel.getFrom());
        toField.textProperty().bindBidirectional(createTourViewModel.getTo());
        transportField.textProperty().bindBidirectional(createTourViewModel.getTransport());
    }

    public void addTour(ActionEvent actionEvent) {
        ResourceBundle res = ResourceBundle.getBundle("org.group07.tourplanner.app." + "gui_strings", Locale.ENGLISH);
        Window owner = submitButton.getScene().getWindow();
        if(nameField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, res.getString("ERROR_FORM"), res.getString("ERROR_NAME"));
            return;
        }
        if(descriptionField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, res.getString("ERROR_FORM"), res.getString("ERROR_DESCRIPTION"));
            return;
        }
        if(fromField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, res.getString("ERROR_FORM"), res.getString("ERROR_FROM"));
            return;
        }
        if(toField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, res.getString("ERROR_FORM"), res.getString("ERROR_TO"));
            return;
        }
        if(transportField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, res.getString("ERROR_FORM"), res.getString("ERROR_TRANSPORT"));
            return;
        }

        this.createTourViewModel.createTour();
    }
}
