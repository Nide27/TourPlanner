package org.group07.tourplanner.app;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.group07.tourplanner.dal.ConfigManager;

import java.io.IOException;

public class Main extends Application {

    private static final Logger logger = LogManager.getLogger(Main.class);

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLDependencyInjection.load("MainWindow.fxml", ConfigManager.getInstance().getLocale());
        Scene scene = new Scene(root);
        stage.setTitle("TourPlanner");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}