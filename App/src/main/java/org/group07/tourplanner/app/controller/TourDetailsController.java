package org.group07.tourplanner.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.group07.tourplanner.app.viewmodel.TourDetailsViewModel;

public class TourDetailsController {
    @FXML
    public TextField nameTextField;

    private final TourDetailsViewModel tourDetailsViewModel;

    public TourDetailsController(TourDetailsViewModel tourDetailsViewModel) {
        this.tourDetailsViewModel = tourDetailsViewModel;
    }

    public TourDetailsViewModel getTourDetailsViewModel() {
        return tourDetailsViewModel;
    }

    @FXML
    void initialize() {
        descriptionTextArea.setEditable(false);
        nameTextField.textProperty().bindBidirectional(tourDetailsViewModel.nameProperty());
    }
}
