package org.group07.tourplanner.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.group07.tourplanner.app.viewmodel.CreateTourViewModel;
import org.group07.tourplanner.app.viewmodel.TourDetailsViewModel;

public class CreateTourController {

    @FXML
    private TextField tourName;
    @FXML
    private TextField description;

    private final CreateTourViewModel createTourViewModel;

    public CreateTourController(CreateTourViewModel createTourViewModel) {
        this.createTourViewModel = createTourViewModel;
    }

    @FXML
    void initialize() {
        tourName.textProperty().bindBidirectional(createTourViewModel.getName());
        tourName.textProperty().bindBidirectional(createTourViewModel.getDescription());
    }

/*    Label secondLabel = new Label("I'm a Label on new Window");

    StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);

    Scene secondScene = new Scene(secondaryLayout, 400, 400);

    // New window (Stage)
    Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(secondScene);

    Window mainWindow = add.getScene().getWindow();



    newWindow.initModality(Modality.WINDOW_MODAL);

    newWindow.initOwner(mainWindow);

    newWindow.show();*/
}
