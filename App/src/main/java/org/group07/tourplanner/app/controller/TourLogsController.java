package org.group07.tourplanner.app.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.app.viewmodel.TourLogsViewModel;
import org.group07.tourplanner.bl.ResourceManager;
import org.group07.tourplanner.dal.DAL;
import org.group07.tourplanner.dal.model.TourLog;

import java.io.IOException;
import java.sql.SQLException;

public class TourLogsController {

    @FXML
    private TableView<TourLog> tourLogsTable;

    private final TourLogsViewModel tourLogsViewModel;

    private final ResourceManager rm;

    private static final Logger logger = LogManager.getLogger(DAL.class);

    public TourLogsController(TourLogsViewModel tourLogsViewModel) {
        this.tourLogsViewModel = tourLogsViewModel;
        this.rm = ResourceManager.getInstance();
    }

    @FXML
    void initialize() {
        tourLogsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tourLogsTable.setItems(tourLogsViewModel.getTourLogsList());
        tourLogsTable.getSelectionModel().selectedItemProperty().addListener(tourLogsViewModel.getChangeListener());
    }

    @FXML
    private void onAdd(ActionEvent actionEvent){
        try {
            this.tourLogsViewModel.createTourLog();
        } catch (IOException e) {
            logger.fatal("FXML error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_FXML"));
        }
    }

    @FXML
    private void onDelete(ActionEvent actionEvent){
        try {
            tourLogsViewModel.deleteTourLog(tourLogsTable.getSelectionModel().getSelectedItem());
        } catch (SQLException e) {
            logger.error("DB error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_DB"));
        }
    }

    @FXML
    private void onEdit(ActionEvent actionEvent){
        if(tourLogsTable.getSelectionModel().getSelectedItem() == null)
            return;

        try {
            this.tourLogsViewModel.editTourLog(tourLogsTable.getSelectionModel().getSelectedItem());
        } catch (IOException e) {
            logger.fatal("FXML error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_FXML"));
        }
    }
}
