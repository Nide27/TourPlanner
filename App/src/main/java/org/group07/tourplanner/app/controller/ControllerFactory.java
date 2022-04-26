package org.group07.tourplanner.app.controller;

import org.group07.tourplanner.app.viewmodel.*;

public class ControllerFactory {

    private final MainWindowViewModel mainWindowViewModel;
    private final TopBarViewModel topBarViewModel;
    private final SearchBarViewModel searchBarViewModel;
    private final TourOverviewViewModel tourOverviewViewModel;
    private final TourDetailsViewModel tourDetailsViewModel;
    private final TourLogsViewModel tourLogsViewModel;
    private final CreateTourViewModel createTourViewModel;

    private ControllerFactory() {
        topBarViewModel = new TopBarViewModel();
        searchBarViewModel = new SearchBarViewModel();
        tourOverviewViewModel = new TourOverviewViewModel();
        tourDetailsViewModel = new TourDetailsViewModel();
        tourLogsViewModel = new TourLogsViewModel();
        createTourViewModel = new CreateTourViewModel();
        mainWindowViewModel = new MainWindowViewModel(topBarViewModel, searchBarViewModel, tourDetailsViewModel, tourOverviewViewModel, tourLogsViewModel, createTourViewModel);
    }

    public Object create(Class<?> controllerClass) {
        if (controllerClass == MainWindowController.class) {
            return new MainWindowController(mainWindowViewModel);
        }

        if (controllerClass == TopBarController.class){
            return new TopBarController(topBarViewModel);
        }

        if (controllerClass == SearchBarController.class){
            return new SearchBarController(searchBarViewModel);
        }

        if (controllerClass == TourDetailsController.class){
            return new TourDetailsController(tourDetailsViewModel);
        }

        if (controllerClass == TourOverviewController.class){
            return new TourOverviewController(tourOverviewViewModel);
        }

        if (controllerClass == TourLogsController.class){
            return new TourLogsController(tourLogsViewModel);
        }

        if (controllerClass == CreateTourController.class){
            return new CreateTourController(createTourViewModel);
        }

        throw new IllegalArgumentException("Unknown controller class: " + controllerClass);
    }

    private static ControllerFactory instance = new ControllerFactory();
    public static ControllerFactory getInstance() {
        return instance;
    }
}
