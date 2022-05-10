package org.group07.tourplanner.app.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.app.viewmodel.CreateTourLogViewModel;
import org.group07.tourplanner.dal.ConfigManager;

import java.util.ResourceBundle;

public class CreateTourLogController {

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

    private final CreateTourLogViewModel createTourLogViewModel;

    @FXML
    void initialize() {
        datePicker.valueProperty().bindBidirectional(createTourLogViewModel.getDatePicker());
        commentField.textProperty().bindBidirectional(createTourLogViewModel.getCommentField());
        difficultyField.textProperty().bindBidirectional(createTourLogViewModel.getDifficultyField());
        durationHourField.textProperty().bindBidirectional(createTourLogViewModel.getDurationHourField());
        durationMinuteField.textProperty().bindBidirectional(createTourLogViewModel.getDurationMinuteField());
        ratingField.textProperty().bindBidirectional(createTourLogViewModel.getRatingField());

       /* ratingField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\b([1-9]|10)\\b")) {
                    System.out.println("d");
                    ratingField.setText(newValue.replaceAll("[^\\d]", ""));

                }
            }
        });*/
    }

    public CreateTourLogController(CreateTourLogViewModel createTourLogViewModel) {
        this.createTourLogViewModel = createTourLogViewModel;
    }

    @FXML
    private void addTourLog(ActionEvent actionEvent){
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
        try {
            Integer.parseInt(ratingField.getText());
            //Check if less than 10
        } catch(Exception e){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, res.getString("ERROR_FORM"), res.getString("ERROR_RATING"));
            return;
        }

        this.createTourLogViewModel.createTourLog();
    }
}
