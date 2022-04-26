package org.group07.tourplanner.app.controller;

import javafx.fxml.FXML;
import org.group07.tourplanner.app.viewmodel.TourLogsViewModel;

public class TourLogsController {

    private final TourLogsViewModel tourLogsViewModel;

    public TourLogsController(TourLogsViewModel tourLogsViewModel) {
        this.tourLogsViewModel = tourLogsViewModel;
    }

    public TourLogsViewModel getLogsViewModel() {
        return tourLogsViewModel;
    }

    @FXML
    void initialize() {
    }
}
