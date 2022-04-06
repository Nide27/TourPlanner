package org.group07.tourplanner.app.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.group07.tourplanner.app.viewmodel.TopBarViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class TopBarController implements Initializable {

    private final TopBarViewModel topBarViewModel;

    public TextField searchTextField;
    public Label testLabel;

    public TopBarController(TopBarViewModel topBarViewModel){
        this.topBarViewModel = new TopBarViewModel();
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void editAction(){}

    @FXML
    protected void fileAction(){}

    @FXML
    protected void optionsAction(){}

    @FXML
    protected void helpAction(){}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void onMenuFileQuitClicked(ActionEvent actionEvent) {
        Platform.exit();
    }
    public void onMenuHelpAboutClicked(ActionEvent actionEvent) {
        Alert aboutBox = new Alert(Alert.AlertType.INFORMATION, "Semesterproject BIF4-SWE2\nby Edin&Vale");
        aboutBox.setTitle("About TourPlanner");
        aboutBox.showAndWait();
    }

}
