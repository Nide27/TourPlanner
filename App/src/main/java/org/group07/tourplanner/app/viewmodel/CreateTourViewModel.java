package org.group07.tourplanner.app.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Objects;

public class CreateTourViewModel {

    @Getter
    private final StringProperty name = new SimpleStringProperty();
    @Getter
    private final StringProperty description = new SimpleStringProperty();
    @SneakyThrows
    public void createWindow(Button button){

        Window mainWindow = button.getScene().getWindow();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/group07/tourplanner/app/CreateTour.fxml")));
        //Label secondLabel = new Label("I'm a Label on new Window");

        //StackPane secondaryLayout = new StackPane(root);
        //secondaryLayout.getChildren().add(secondLabel);

        Scene secondScene = new Scene(root);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(secondScene);

        newWindow.initModality(Modality.WINDOW_MODAL);

        newWindow.initOwner(mainWindow);

        newWindow.show();
    }

}
