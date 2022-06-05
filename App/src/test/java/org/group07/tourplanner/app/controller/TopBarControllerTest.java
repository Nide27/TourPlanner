package org.group07.tourplanner.app.controller;

import com.lowagie.text.DocumentException;
import org.group07.tourplanner.app.viewmodel.TopBarViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TopBarControllerTest {

    TopBarController topBarController;
    TopBarViewModel topBarViewModel;

    @BeforeEach
    void setup(){
        topBarViewModel = mock(TopBarViewModel.class);
        topBarController = new TopBarController(topBarViewModel);
    }

    @Test
    void onMenuTourReport() throws IOException, DocumentException, SQLException {
        topBarController.onMenuTourReport(null);

        verify(topBarViewModel).createTourReport();
    }

    @Test
    void onMenuSummarizedReport() throws IOException, DocumentException, SQLException {
        topBarController.onMenuSummarizedReport(null);

        verify(topBarViewModel).createSummarizedReport();
    }

    @Test
    void onFileImport() throws IOException, SQLException {
        topBarController.onFileImport(null);

        verify(topBarViewModel).importFile();
    }

    @Test
    void onFileExport() throws IOException, SQLException {
        topBarController.onFileExport(null);

        verify(topBarViewModel).exportFile();
    }
}