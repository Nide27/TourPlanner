package org.group07.tourplanner.app.viewmodel;

import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.bl.BL;

public class MainWindowViewModel {

    private TopBarViewModel topBarViewModel;
    private SearchBarViewModel searchBarViewModel;
    private TourDetailsViewModel tourDetailsViewModel;
    private TourOverviewViewModel tourOverviewViewModel;
    private TourLogsViewModel tourLogsViewModel;

    public MainWindowViewModel(TopBarViewModel topBarViewModel, SearchBarViewModel searchBarViewModel, TourDetailsViewModel tourDetailsViewModel, TourOverviewViewModel tourOverviewViewModel, TourLogsViewModel tourLogsViewModel) {
        this.topBarViewModel = topBarViewModel;
        this.searchBarViewModel = searchBarViewModel;
        this.tourDetailsViewModel = tourDetailsViewModel;
        this.tourOverviewViewModel = tourOverviewViewModel;
        this.tourLogsViewModel = tourLogsViewModel;

        this.searchBarViewModel.addSearchListener(searchString -> searchTours(searchString));
        this.tourOverviewViewModel.addSelectionChangedListener(selectTour -> selectTour(selectTour));
    }

    private void selectTour(TourItem selectedTourItem) {
        tourLogsViewModel.setTourItem(selectedTourItem);
        tourDetailsViewModel.setTourItem(selectedTourItem);
        topBarViewModel.setTourItem(selectedTourItem);
    }

    private void searchTours(String searchString) {
        var tours = BL.getInstance().findMatchingTours( searchString );
        tourOverviewViewModel.setTours(tours);
    }
}
