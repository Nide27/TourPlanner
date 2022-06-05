package org.group07.tourplanner.app.controller;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import org.group07.tourplanner.app.viewmodel.TourOverviewViewModel;
import org.group07.tourplanner.dal.model.TourItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TourOverviewControllerTest {

    TourOverviewController tourOverviewController;
    TourOverviewViewModel tourOverviewViewModel;

    @BeforeEach
    void setup() {
        this.tourOverviewViewModel = mock(TourOverviewViewModel.class);
        this.tourOverviewController = new TourOverviewController(tourOverviewViewModel);
    }

    @Test
    void onAdd() throws IOException {
        tourOverviewController.onAdd(null);

        verify(tourOverviewViewModel).createTour();
    }
}