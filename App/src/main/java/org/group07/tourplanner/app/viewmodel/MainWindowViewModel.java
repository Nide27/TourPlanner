package org.group07.tourplanner.app.viewmodel;

import javafx.scene.control.Alert;
import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.bl.ResourceManager;
import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.bl.BL;
import org.group07.tourplanner.dal.logger.LogManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class MainWindowViewModel {

    private TopBarViewModel topBarViewModel;
    private SearchBarViewModel searchBarViewModel;
    private TourDetailsViewModel tourDetailsViewModel;
    private TourOverviewViewModel tourOverviewViewModel;
    private TourLogsViewModel tourLogsViewModel;

    private final ResourceManager rm;

    public MainWindowViewModel(TopBarViewModel topBarViewModel, SearchBarViewModel searchBarViewModel, TourDetailsViewModel tourDetailsViewModel, TourOverviewViewModel tourOverviewViewModel, TourLogsViewModel tourLogsViewModel) {
        this.topBarViewModel = topBarViewModel;
        this.searchBarViewModel = searchBarViewModel;
        this.tourDetailsViewModel = tourDetailsViewModel;
        this.tourOverviewViewModel = tourOverviewViewModel;
        this.tourLogsViewModel = tourLogsViewModel;

        this.searchBarViewModel.addSearchListener(searchString -> searchTours(searchString));
        this.tourOverviewViewModel.addSelectionChangedListener(selectTour -> selectTour(selectTour));

        this.rm = ResourceManager.getInstance();
    }

    private void selectTour(TourItem selectedTourItem) {
        try {
            tourLogsViewModel.setTourItem(selectedTourItem);
            tourDetailsViewModel.setTourItem(selectedTourItem);
            topBarViewModel.setTourItem(selectedTourItem);
        } catch (FileNotFoundException e) {
            LogManager.getLogger().warn("Could not retrieve loading image:\n" + e);
        } catch (IOException e) {
            LogManager.getLogger().warn("Could not retrieve config:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_MQ"));
        }  catch (SQLException e) {
            LogManager.getLogger().error("DB error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_DB"));
        }
    }

    private void searchTours(String searchString) {
        try {
            var tours = BL.getInstance().findMatchingTours( searchString );
            tourOverviewViewModel.setTours(tours);
        } catch (SQLException e) {
            LogManager.getLogger().error("DB error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_DB"));
        }
    }
}
