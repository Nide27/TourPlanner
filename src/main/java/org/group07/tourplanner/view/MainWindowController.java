package org.group07.tourplanner.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.group07.tourplanner.viewmodel.MainWindowViewModel;

import java.net.URL;
import java.util.ResourceBundle;

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