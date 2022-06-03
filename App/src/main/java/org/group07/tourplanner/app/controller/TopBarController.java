package org.group07.tourplanner.app.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lowagie.text.DocumentException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.group07.tourplanner.app.helper.AlertHelper;
import org.group07.tourplanner.bl.PdfGenerator;
import org.group07.tourplanner.bl.ResourceManager;
import org.group07.tourplanner.app.viewmodel.TopBarViewModel;

public class TopBarController {

    private final TopBarViewModel topBarViewModel;

    private final ResourceManager rm;

    private static final Logger logger = LogManager.getLogger(PdfGenerator.class);

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
        try {
            topBarViewModel.createTourReport();
        } catch (FileNotFoundException e) {
            logger.error("Could not open file when generating PDF:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_FNF_MSG"));
        } catch (IOException e) {
            logger.error("Could not create PDF:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_IO_O_MSG"));
        } catch (DocumentException e) {
            logger.error("Could not close OutputStream:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_IO_O_MSG"));
        } catch (SQLException e) {
            logger.error("DB error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_DB"));
        }
    }

    @FXML
    private void onMenuSummarizedReport(ActionEvent actionEvent){
        try {
            topBarViewModel.createSummarizedReport();
        } catch (FileNotFoundException e) {
            logger.error("Could not open file when generating PDF:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_FNF_MSG"));
        } catch (IOException e) {
            logger.error("Could not create PDF:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_IO_O_MSG"));
        } catch (DocumentException e) {
            logger.error("Could not close OutputStream:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_IO_O_MSG"));
        } catch (SQLException e) {
            logger.error("DB error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_DB"));
        }
    }

    @FXML
    private void onFileImport(ActionEvent actionEvent){
        try {
            topBarViewModel.importFile();
        } catch (JsonProcessingException e) {
            logger.error("Could not parse JSON:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_JSON_MSG"));
        } catch (FileNotFoundException e) {
            logger.error("Could not find specified file:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_FNF_MSG"));
        } catch (IOException e) {
            logger.error("Could not read from file:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_IO_I_MSG"));
        } catch (SQLException e) {
            logger.error("DB error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_DB"));
        }
    }

    @FXML
    private void onFileExport(ActionEvent actionEvent){
        try {
            topBarViewModel.exportFile();
        } catch (JsonProcessingException e) {
            logger.error("Could not parse JSON:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_JSON_MSG"));
        } catch (IOException e) {
            logger.error("Could not create/write to file:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_IO_O_MSG"));
        } catch (SQLException e) {
            logger.error("DB error:\n" + e);
            AlertHelper.showAlert(Alert.AlertType.ERROR, rm.load("ALERT_ERROR_TITLE"), rm.load("ALERT_ERROR_DB"));
        }
    }
}
