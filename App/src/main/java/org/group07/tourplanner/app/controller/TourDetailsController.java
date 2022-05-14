package org.group07.tourplanner.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import lombok.Getter;
import org.group07.tourplanner.app.viewmodel.TourDetailsViewModel;

public class TourDetailsController {

    @FXML
    private ImageView imageView;
    @FXML
    private TextField nameTextField;
    @FXML
    private Text descriptionText;
    @FXML
    private Text distanceText;
    @FXML
    private Text estimateText;

    @Getter
    private final TourDetailsViewModel tourDetailsViewModel;

    public TourDetailsController(TourDetailsViewModel tourDetailsViewModel) {
        this.tourDetailsViewModel = tourDetailsViewModel;
    }

    @FXML
    void initialize() {
        //descriptionTextArea.setEditable(false);
        nameTextField.textProperty().bindBidirectional(tourDetailsViewModel.getName());
        //descriptionTextArea.textProperty().bindBidirectional(tourDetailsViewModel.getDescription());
        imageView.imageProperty().bindBidirectional(tourDetailsViewModel.getImageView());
        System.out.println(imageView.getParent());
        descriptionText.textProperty().bindBidirectional(tourDetailsViewModel.getDescription());
        distanceText.textProperty().bindBidirectional(tourDetailsViewModel.getDistance());
        estimateText.textProperty().bindBidirectional(tourDetailsViewModel.getEstimate());

    }
}
