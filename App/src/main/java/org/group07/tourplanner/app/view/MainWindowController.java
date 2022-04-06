package org.group07.tourplanner.app.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.group07.tourplanner.app.viewmodel.MainWindowViewModel;

public class MainWindowController {
    @FXML private TopBarController topBarController;    // injected controller of SearchBar.fxml

    private final MainWindowViewModel mainViewModel;

    public MainWindowController(MainWindowViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    public MainWindowViewModel getMainViewModel() {
        return mainViewModel;
    }

    @FXML void initialize() {
    }


}