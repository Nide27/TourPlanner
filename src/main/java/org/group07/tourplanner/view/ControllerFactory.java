package org.group07.tourplanner.view;

import org.group07.tourplanner.viewmodel.MainWindowViewModel;
import org.group07.tourplanner.viewmodel.TopBarViewModel;

public class ControllerFactory {

    private final MainWindowViewModel mainWindowViewModel;
    private final TopBarViewModel topBarViewModel;

    private ControllerFactory() {
        topBarViewModel = new TopBarViewModel();
        mainWindowViewModel = new MainWindowViewModel(topBarViewModel);
    }

    public Object create(Class<?> controllerClass) {
        if (controllerClass == MainWindowController.class) {
            return new MainWindowController(mainWindowViewModel);
        }else if (controllerClass == TopBarController.class){
            return new TopBarController(topBarViewModel);
        }
        throw new IllegalArgumentException("Unknown controller class: " + controllerClass);
    }

    private static ControllerFactory instance = new ControllerFactory();
    public static ControllerFactory getInstance() {
        return instance;
    }
}
