package org.group07.tourplanner.app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.Getter;
import org.group07.tourplanner.app.viewmodel.TourLogsViewModel;
import org.group07.tourplanner.dal.model.TourLog;

public class TourLogsController {

    @FXML
    private TableView tourLogsTable;

    @FXML
    private TableColumn dateColumn;

    @FXML
    private TableColumn commentColumn;

    @FXML
    private TableColumn difficultyColumn;

    @FXML
    private TableColumn durationColumn;

    @FXML
    private TableColumn ratingColumn;

    private final TourLogsViewModel tourLogsViewModel;

    public TourLogsController(TourLogsViewModel tourLogsViewModel) {
        this.tourLogsViewModel = tourLogsViewModel;
    }

    public TourLogsViewModel getLogsViewModel() {
        return tourLogsViewModel;
    }

    @FXML
    void initialize() {
        tourLogsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tourLogsTable.setItems(tourLogsViewModel.getTourLogsList());
    }
}
