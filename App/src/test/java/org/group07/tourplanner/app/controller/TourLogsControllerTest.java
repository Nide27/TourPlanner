package org.group07.tourplanner.app.controller;

import org.group07.tourplanner.app.viewmodel.TourLogsViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TourLogsControllerTest {

    TourLogsController tourLogsController;
    TourLogsViewModel tourLogsViewModel;

    @BeforeEach
    void setup() {
        this.tourLogsViewModel = mock(TourLogsViewModel.class);
        this.tourLogsController = new TourLogsController(tourLogsViewModel);
    }

    @Test
    void onAdd() throws IOException {
        tourLogsController.onAdd(null);

        verify(tourLogsViewModel).createTourLog();
    }
}