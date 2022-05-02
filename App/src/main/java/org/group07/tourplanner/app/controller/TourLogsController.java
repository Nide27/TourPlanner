package org.group07.tourplanner.app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.Getter;
import org.group07.tourplanner.app.viewmodel.TourLogsViewModel;
import org.group07.tourplanner.dal.model.TourLog;

public class TourLogsController {

    @FXML
    private Button delButton;

    @FXML
    private TableView<TourLog> tourLogsTable;

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

    @FXML
    void initialize() {
        tourLogsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tourLogsTable.setItems(tourLogsViewModel.getTourLogsList());
        tourLogsTable.getSelectionModel().selectedItemProperty().addListener(tourLogsViewModel.getChangeListener());
    }

    @FXML
    private void onButtonDel(ActionEvent actionEvent){
        tourLogsViewModel.deleteTourLog(tourLogsTable.getSelectionModel().getSelectedItem());
    }
}
