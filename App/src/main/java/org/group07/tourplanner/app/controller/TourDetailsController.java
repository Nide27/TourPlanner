package org.group07.tourplanner.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Getter;
import org.group07.tourplanner.app.viewmodel.TourDetailsViewModel;

public class TourDetailsController {
    @FXML
    private TextField nameTextField;

    @FXML
    private TextArea descriptionTextArea;

    @Getter
    private final TourDetailsViewModel tourDetailsViewModel;

    public TourDetailsController(TourDetailsViewModel tourDetailsViewModel) {
        this.tourDetailsViewModel = tourDetailsViewModel;
    }

    @FXML
    void initialize() {
        descriptionTextArea.setEditable(false);
        nameTextField.textProperty().bindBidirectional(tourDetailsViewModel.nameProperty());
        descriptionTextArea.textProperty().bindBidirectional(tourDetailsViewModel.descriptionProperty());
    }
}
