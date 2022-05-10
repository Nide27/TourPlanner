package org.group07.tourplanner.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.app.viewmodel.EditTourLogViewModel;
import org.group07.tourplanner.dal.ConfigManager;

import java.util.ResourceBundle;

public class EditTourLogController {

    @FXML
    private Button submitButton;

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

    @FXML
    void initialize() {
        datePicker.valueProperty().bindBidirectional(editTourLogViewModel.getDatePicker());
        commentField.textProperty().bindBidirectional(editTourLogViewModel.getCommentField());
        difficultyField.textProperty().bindBidirectional(editTourLogViewModel.getDifficultyField());
        durationHourField.textProperty().bindBidirectional(editTourLogViewModel.getDurationHourField());
        durationMinuteField.textProperty().bindBidirectional(editTourLogViewModel.getDurationMinuteField());
        ratingField.textProperty().bindBidirectional(editTourLogViewModel.getRatingField());
    }

    public EditTourLogController(EditTourLogViewModel editTourLogViewModel) {
        this.editTourLogViewModel = editTourLogViewModel;
    }

    @FXML
    private void editTourLog(ActionEvent actionEvent){
        ResourceBundle res = ResourceBundle.getBundle("org.group07.tourplanner.app." + "gui_strings", ConfigManager.getInstance().getLocale());
        Window owner = submitButton.getScene().getWindow();

        if(datePicker.getValue() == null){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, res.getString("ERROR_FORM"), res.getString("ERROR_DATE"));
            return;
        }
        if(commentField.getText() == null || commentField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, res.getString("ERROR_FORM"), res.getString("ERROR_COMMENT"));
            return;
        }
        if(difficultyField.getText() == null || difficultyField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, res.getString("ERROR_FORM"), res.getString("ERROR_DIFFICULTY"));
            return;
        }
        if((durationHourField.getText() == null && durationMinuteField.getText() == null) || (durationHourField.getText().isEmpty() && durationMinuteField.getText().isEmpty())){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, res.getString("ERROR_FORM"), res.getString("ERROR_DURATION"));
            return;
        }
        if(ratingField.getText() == null || ratingField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, res.getString("ERROR_FORM"), res.getString("ERROR_RATING"));
            return;
        }

        this.editTourLogViewModel.editTourLog();
    }
}
