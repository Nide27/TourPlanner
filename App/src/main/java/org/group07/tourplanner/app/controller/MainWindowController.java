package org.group07.tourplanner.app.controller;

import javafx.fxml.FXML;
import org.group07.tourplanner.app.viewmodel.MainWindowViewModel;

public class MainWindowController {

    private final MainWindowViewModel mainViewModel;

    public MainWindowController(MainWindowViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    @FXML void initialize() {  }
}