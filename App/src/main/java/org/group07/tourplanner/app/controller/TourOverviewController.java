package org.group07.tourplanner.app.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import lombok.Getter;

import lombok.Setter;

import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.app.viewmodel.TourOverviewViewModel;
import org.group07.tourplanner.bl.ResourceManager;
import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.dal.logger.LogManager;

import java.io.IOException;
import java.sql.SQLException;

public class TourOverviewController {
    @Setter
    @FXML
    private ListView tourItemList;

    @Getter
    private final TourOverviewViewModel tourOverviewViewModel;

    private final ResourceManager rm;

    public TourOverviewController(TourOverviewViewModel tourOverviewViewModel) {
        this.tourOverviewViewModel = tourOverviewViewModel;
        this.rm = ResourceManager.getInstance();
    }

    @FXML
    void initialize() {
        tourItemList.setItems(tourOverviewViewModel.getObservableTourItems());
        tourItemList.getSelectionModel().selectedItemProperty().addListener(tourOverviewViewModel.getChangeListener());
    }

    public void onAdd(ActionEvent actionEvent) {
        try {
            tourOverviewViewModel.createTour();
        } catch (IOException e) {
            LogManager.getLogger().fatal("FXML error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_FXML"));
        }
    }

    public void onRemove(ActionEvent actionEvent) {
        try {
            tourOverviewViewModel.deleteTour((TourItem) tourItemList.getSelectionModel().getSelectedItem());
        } catch (SQLException e) {
            LogManager.getLogger().error("DB error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_DB"));
        }
    }

    public void onEdit(ActionEvent actionEvent){
        if(tourItemList.getSelectionModel().getSelectedItem() == null)
            return;

        try {
            tourOverviewViewModel.updateTour((TourItem) tourItemList.getSelectionModel().getSelectedItem());
        } catch (IOException e) {
            LogManager.getLogger().fatal("FXML error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_FXML"));
        } catch (SQLException e) {
            LogManager.getLogger().error("DB error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_DB"));
        }
    }
}
