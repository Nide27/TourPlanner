package org.group07.tourplanner.app.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lowagie.text.DocumentException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;


import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.bl.ResourceManager;
import org.group07.tourplanner.app.viewmodel.TopBarViewModel;
import org.group07.tourplanner.dal.logger.LogManager;

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

    public void onMenuTourReport(ActionEvent actionEvent){
        try {
            topBarViewModel.createTourReport();
        } catch (FileNotFoundException e) {
            LogManager.getLogger().error("Could not open file when generating PDF:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_FNF_MSG"));
        } catch (IOException e) {
            LogManager.getLogger().error("Could not create PDF:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_IO_O_MSG"));
        } catch (DocumentException e) {
            LogManager.getLogger().error("Could not close OutputStream:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_IO_O_MSG"));
        } catch (SQLException e) {
            LogManager.getLogger().error("DB error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_DB"));
        }
    }

    public void onMenuSummarizedReport(ActionEvent actionEvent){
        try {
            topBarViewModel.createSummarizedReport();
        } catch (FileNotFoundException e) {
            LogManager.getLogger().error("Could not open file when generating PDF:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_FNF_MSG"));
        } catch (IOException e) {
            LogManager.getLogger().error("Could not create PDF:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_IO_O_MSG"));
        } catch (DocumentException e) {
            LogManager.getLogger().error("Could not close OutputStream:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_IO_O_MSG"));
        } catch (SQLException e) {
            LogManager.getLogger().error("DB error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_DB"));
        }
    }

    public void onFileImport(ActionEvent actionEvent){
        try {
            topBarViewModel.importFile();
        } catch (JsonProcessingException | NullPointerException e) {
            LogManager.getLogger().error("Could not parse JSON:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_JSON_MSG"));
        } catch (FileNotFoundException e) {
            LogManager.getLogger().error("Could not find specified file:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_FNF_MSG"));
        } catch (IOException e) {
            LogManager.getLogger().error("Could not read from file:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_IO_I_MSG"));
        } catch (SQLException e) {
            LogManager.getLogger().error("DB error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_DB"));
        }
    }

    public void onFileExport(ActionEvent actionEvent){
        try {
            topBarViewModel.exportFile();
        } catch (JsonProcessingException e) {
            LogManager.getLogger().error("Could not parse JSON:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_JSON_MSG"));
        } catch (IOException e) {
            LogManager.getLogger().error("Could not create/write to file:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_IO_O_MSG"));
        } catch (SQLException e) {
            LogManager.getLogger().error("DB error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_DB"));
        }
    }
}
