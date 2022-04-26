package org.group07.tourplanner.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Getter;
import org.group07.tourplanner.app.viewmodel.CreateTourViewModel;
import org.group07.tourplanner.app.viewmodel.TourOverviewViewModel;
import org.group07.tourplanner.dal.model.TourItem;

public class TourOverviewController {
    @FXML
    private ListView<TourItem> tourItemList;

    @FXML
    private Button add;

    @Getter
    private final TourOverviewViewModel tourOverviewViewModel;

    public TourOverviewController(TourOverviewViewModel tourOverviewViewModel) {
        this.tourOverviewViewModel = tourOverviewViewModel;
    }

    @FXML
    void initialize() {
        tourItemList.setItems(tourOverviewViewModel.getObservableTourItems());
        tourItemList.getSelectionModel().selectedItemProperty().addListener(tourOverviewViewModel.getChangeListener());
    }

    @FXML
    private void onButtonAdd(ActionEvent actionEvent) {

        //tourOverviewViewModel.addNewTour();
        // Neue Stage erstellen und dort neue Tour adden


        tourItemList.getSelectionModel().selectLast();
    }

    @FXML
    private void onButtonRemove(ActionEvent actionEvent) {
        tourOverviewViewModel.deleteTour(tourItemList.getSelectionModel().getSelectedItem());
    }
}
