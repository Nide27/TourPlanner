package org.group07.tourplanner.app.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import lombok.Getter;
import org.group07.tourplanner.app.viewmodel.TourDetailsViewModel;

import javax.naming.Binding;

public class TourDetailsController {

    @FXML
    private ImageView imageView;
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
        nameTextField.textProperty().bindBidirectional(tourDetailsViewModel.getName());
        descriptionTextArea.textProperty().bindBidirectional(tourDetailsViewModel.getDescription());
        imageView.imageProperty().bindBidirectional(tourDetailsViewModel.getImageView());
    }
}
