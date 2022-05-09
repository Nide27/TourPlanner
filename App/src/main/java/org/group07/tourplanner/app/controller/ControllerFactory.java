package org.group07.tourplanner.app.controller;

import org.group07.tourplanner.app.viewmodel.*;

public class ControllerFactory {

    private final MainWindowViewModel mainWindowViewModel;
    private final TopBarViewModel topBarViewModel;
    private final SearchBarViewModel searchBarViewModel;
    private final TourOverviewViewModel tourOverviewViewModel;
    private final TourDetailsViewModel tourDetailsViewModel;
    private final TourLogsViewModel tourLogsViewModel;
    private final CreateTourItemViewModel createTourItemViewModel;
    private final EditTourItemViewModel editTourItemViewModel;
    private final EditTourLogViewModel editTourLogViewModel;
    private final CreateTourLogViewModel createTourLogViewModel;

    private ControllerFactory() {
        topBarViewModel = new TopBarViewModel();
        searchBarViewModel = new SearchBarViewModel();
        createTourItemViewModel = new CreateTourItemViewModel();
        createTourLogViewModel = new CreateTourLogViewModel();
        editTourItemViewModel = new EditTourItemViewModel();
        editTourLogViewModel = new EditTourLogViewModel();
        tourOverviewViewModel = new TourOverviewViewModel(createTourItemViewModel, editTourItemViewModel);
        tourDetailsViewModel = new TourDetailsViewModel();
        tourLogsViewModel = new TourLogsViewModel(createTourLogViewModel, editTourLogViewModel);
        mainWindowViewModel = new MainWindowViewModel(topBarViewModel, searchBarViewModel, tourDetailsViewModel, tourOverviewViewModel, tourLogsViewModel, createTourItemViewModel, createTourLogViewModel);
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

        if (controllerClass == CreateTourItemController.class){
            return new CreateTourItemController(createTourItemViewModel);
        }

        if (controllerClass == CreateTourLogController.class){
            return new CreateTourLogController(createTourLogViewModel);
        }

        if(controllerClass == EditTourItemController.class){
            return new EditTourItemController(editTourItemViewModel);
        }

        if(controllerClass == EditTourLogController.class){
            return new EditTourLogController(editTourLogViewModel);
        }

        throw new IllegalArgumentException("Unknown controller class: " + controllerClass);
    }

    private static ControllerFactory instance = new ControllerFactory();
    public static ControllerFactory getInstance() {
        return instance;
    }
}
