package org.group07.tourplanner.app.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.group07.tourplanner.app.viewmodel.TourLogsViewModel;
import org.group07.tourplanner.dal.model.TourLog;

public class TourLogsController {

    @FXML
    private TableView<TourLog> tourLogsTable;

    private final TourLogsViewModel tourLogsViewModel;

    public TourLogsController(TourLogsViewModel tourLogsViewModel) {
        this.tourLogsViewModel = tourLogsViewModel;
    }

    @FXML
    void initialize() {
        tourLogsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tourLogsTable.setItems(tourLogsViewModel.getTourLogsList());
        tourLogsTable.getSelectionModel().selectedItemProperty().addListener(tourLogsViewModel.getChangeListener());
    }

    @FXML
    private void onAdd(ActionEvent actionEvent){
        this.tourLogsViewModel.createTourLog();
    }

    @FXML
    private void onDelete(ActionEvent actionEvent){
        tourLogsViewModel.deleteTourLog(tourLogsTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void onEdit(ActionEvent actionEvent){
        if(tourLogsTable.getSelectionModel().getSelectedItem() == null)
            return;

        this.tourLogsViewModel.editTourLog(tourLogsTable.getSelectionModel().getSelectedItem());
    }
}
