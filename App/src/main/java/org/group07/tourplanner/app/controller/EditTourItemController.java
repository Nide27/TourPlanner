package org.group07.tourplanner.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.app.viewmodel.EditTourItemViewModel;

import java.util.Locale;
import java.util.ResourceBundle;

public class EditTourItemController {

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

    private final EditTourItemViewModel editTourItemViewModel;

    public EditTourItemController(EditTourItemViewModel editTourItemViewModel){
        this.editTourItemViewModel = editTourItemViewModel;
    }

    @FXML
    void initialize() {
        nameField.textProperty().bindBidirectional(editTourItemViewModel.getName());
        descriptionField.textProperty().bindBidirectional(editTourItemViewModel.getDescription());
        fromField.textProperty().bindBidirectional(editTourItemViewModel.getFrom());
        toField.textProperty().bindBidirectional(editTourItemViewModel.getTo());
        transportField.textProperty().bindBidirectional(editTourItemViewModel.getTransport());
    }

    @FXML
    private void editTour(ActionEvent actionEvent){
        ResourceBundle res = ResourceBundle.getBundle("org.group07.tourplanner.app." + "gui_strings", Locale.ENGLISH);
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
        if(transportField.getText() == null || transportField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, res.getString("ERROR_FORM"), res.getString("ERROR_TRANSPORT"));
            return;
        }

        this.editTourItemViewModel.editTour();
    }
}
