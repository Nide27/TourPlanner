package org.group07.tourplanner.app.viewmodel;

import org.group07.tourplanner.dal.TourItemDao;
import org.group07.tourplanner.dal.model.TourItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

class TourOverviewViewModelTest {

    TourItem tourItem;
    TourItemDao tourItemDao;
    TourOverviewViewModel tourOverviewViewModel;
    CreateTourItemViewModel createTourItemViewModel;
    EditTourItemViewModel editTourItemViewModel;

    @BeforeEach
    private void setup() {
        tourItem = new TourItem(1, "Tour", "Description", "From", "To", "PEDESTRIAN", 1.0, "00:15:00");
        tourItemDao = mock(TourItemDao.class);
        createTourItemViewModel = mock(CreateTourItemViewModel.class);
        editTourItemViewModel = mock(EditTourItemViewModel.class);
        tourOverviewViewModel = new TourOverviewViewModel(createTourItemViewModel, editTourItemViewModel, tourItemDao);
    }

    @Test
    void createTour() throws IOException {
        tourOverviewViewModel.createTour();

        verify(createTourItemViewModel).createWindow(tourOverviewViewModel.getObservableTourItems());
    }

    @Test
    void deleteTour() throws SQLException {
        tourOverviewViewModel.deleteTour(tourItem);

        verify(tourItemDao).delete(tourItem);
    }

    @Test
    void updateTour() throws SQLException, IOException {
        when(tourItemDao.get(anyInt())).thenReturn(Optional.ofNullable(tourItem));
        when(tourItemDao.getAll()).thenReturn(new ArrayList<TourItem>());

        tourOverviewViewModel.updateTour(tourItem);

        verify(editTourItemViewModel).createWindow(tourOverviewViewModel.getObservableTourItems(), tourItem);
    }
}