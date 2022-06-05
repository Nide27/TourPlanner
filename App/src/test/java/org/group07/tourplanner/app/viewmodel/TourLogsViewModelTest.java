package org.group07.tourplanner.app.viewmodel;

import org.group07.tourplanner.app.controller.CreateTourLogController;
import org.group07.tourplanner.dal.TourItemDao;
import org.group07.tourplanner.dal.TourLogDao;
import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.dal.model.TourLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TourLogsViewModelTest {

    TourLog tourLog;
    TourItem tourItem;
    TourLogDao tourLogDao;
    TourLogsViewModel tourLogsViewModel;
    CreateTourLogViewModel createTourLogViewModel;
    EditTourLogViewModel editTourLogViewModel;

    @BeforeEach
    private void setup() {
        tourLog = new TourLog(0, LocalDate.of(2022, 1, 27), "Comment1", 5, 15, 5);
        tourItem = new TourItem(1, "Tour", "Description", "From", "To", "PEDESTRIAN", 1.0, "00:15:00");
        tourLogDao = mock(TourLogDao.class);
        createTourLogViewModel = mock(CreateTourLogViewModel.class);
        editTourLogViewModel = mock(EditTourLogViewModel.class);
        tourLogsViewModel = new TourLogsViewModel(createTourLogViewModel, editTourLogViewModel, tourLogDao);
    }

    @Test
    void createTourLog() throws IOException, SQLException {

        tourLogsViewModel.setTourItem(tourItem);
        tourLogsViewModel.createTourLog();

        verify(createTourLogViewModel).createWindow(tourLogsViewModel.getTourLogsList(), tourItem);
    }

    @Test
    void deleteTourLog() throws SQLException {
        tourLogsViewModel.deleteTourLog(tourLog);

        verify(tourLogDao).delete(tourLog);
    }

    @Test
    void updateTourLog() throws IOException {
        tourLogsViewModel.updateTourLog(tourLog);

        verify(editTourLogViewModel).createWindow(tourLogsViewModel.getTourLogsList(), tourLog);
    }
}