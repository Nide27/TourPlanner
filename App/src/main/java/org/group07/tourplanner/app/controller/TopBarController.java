package org.group07.tourplanner.app.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.app.helper.ResourceManager;
import org.group07.tourplanner.app.viewmodel.TopBarViewModel;

public class TopBarController {

    private final TopBarViewModel topBarViewModel;

    private final ResourceManager rm;

    public TopBarController(TopBarViewModel topBarViewModel){
        this.topBarViewModel = topBarViewModel;

        rm = ResourceManager.getInstance();
    }

    @FXML
    void initialize(){  }

    @FXML
    private void onMenuHelpAboutClicked(ActionEvent actionEvent) {
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, rm.load("ALERT_INFO_ABOUT_TITLE"), rm.load("ALERT_INFO_ABOUT_MSG"));
    }

    @FXML
    private void onMenuTourReport(ActionEvent actionEvent){
        topBarViewModel.createTourReport();
    }

    @FXML
    private void onMenuSummarizedReport(ActionEvent actionEvent){ topBarViewModel.createSummarizedReport(); }

    @FXML
    private void onFileImport(ActionEvent actionEvent){
        try {
            topBarViewModel.importFile();
        } catch (JsonProcessingException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_JSON_MSG"));
        } catch (FileNotFoundException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_FNF_MSG"));
        } catch (IOException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_IO_I_MSG"));
        }
    }

    @FXML
    private void onFileExport(ActionEvent actionEvent){
        try {
            topBarViewModel.exportFile();
        } catch (JsonProcessingException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_JSON_MSG"));
        } catch (IOException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_IO_O_MSG"));
        }
    }
}
