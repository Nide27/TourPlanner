package org.group07.tourplanner.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.group07.tourplanner.viewmodel.MainWindowViewModel;
import org.group07.tourplanner.viewmodel.TopBarViewModel;

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

    @FXML
    protected void searchAction(){
        topBarViewModel.saveValue();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        searchTextField.textProperty().bindBidirectional(topBarViewModel.getSearchResult());
        testLabel.textProperty().bind(topBarViewModel.setLabel());
    }

}