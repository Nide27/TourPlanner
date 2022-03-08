package org.group07.tourplanner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final MainViewModel viewModel = new MainViewModel();

    public TextField searchTextField;
    public Label testLabel;

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
        viewModel.saveValue();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        searchTextField.textProperty().bindBidirectional(viewModel.getSearchResult());
        testLabel.textProperty().bind(viewModel.setLabel());
    }
}