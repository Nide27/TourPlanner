package org.group07.tourplanner.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import lombok.Getter;
import org.group07.tourplanner.app.viewmodel.TourOverviewViewModel;
import org.group07.tourplanner.dal.model.TourItem;

public class TourOverviewController {
    @FXML
    private ListView<TourItem> tourItemList;

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
        tourOverviewViewModel.addNewTour();
    }

    @FXML
    private void onButtonRemove(ActionEvent actionEvent) {
        tourOverviewViewModel.deleteTour(tourItemList.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void onButtonEdit(ActionEvent actionEvent){
        if(tourItemList.getSelectionModel().getSelectedItem() == null)
            return;

        tourOverviewViewModel.updateTour(tourItemList.getSelectionModel().getSelectedItem());
    }
}
