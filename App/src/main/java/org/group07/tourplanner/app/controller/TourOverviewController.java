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
    public ListView<TourItem> tourItemList;

    private final TourOverviewViewModel tourOverviewViewModel;

    public TourOverviewController(TourOverviewViewModel tourOverviewViewModel) {
        this.tourOverviewViewModel = tourOverviewViewModel;
    }

    public TourOverviewViewModel getTourOverviewViewModel() {
        return tourOverviewViewModel;
    }

    @FXML
    void initialize() {
        tourItemList.setItems(tourOverviewViewModel.getObservableTours());
        tourItemList.getSelectionModel().selectedItemProperty().addListener(tourOverviewViewModel.getChangeListener());
    }

    public void onButtonAdd(ActionEvent actionEvent) {
        tourOverviewViewModel.addNewTour();
        tourItemList.getSelectionModel().selectLast();
    }

    public void onButtonRemove(ActionEvent actionEvent) {
        tourOverviewViewModel.deleteTour(tourItemList.getSelectionModel().getSelectedItem());
    }
}
