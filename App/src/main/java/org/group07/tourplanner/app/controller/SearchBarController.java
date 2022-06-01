package org.group07.tourplanner.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import org.group07.tourplanner.app.viewmodel.SearchBarViewModel;

public class SearchBarController {

    @FXML
    public Button searchButton;
    @FXML
    public TextField searchTextField;

    private final SearchBarViewModel searchBarViewModel;

    public SearchBarController(SearchBarViewModel searchBarViewModel) {
        this.searchBarViewModel = searchBarViewModel;
    }

    @FXML
    void initialize() {
        searchTextField.textProperty().bindBidirectional( searchBarViewModel.searchStringProperty() );
    }

    public void onSearch(ActionEvent actionEvent) {
        searchBarViewModel.doSearch();
    }
}
