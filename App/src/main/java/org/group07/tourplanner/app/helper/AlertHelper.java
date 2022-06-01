package org.group07.tourplanner.app.helper;

import javafx.scene.control.Alert;

public class AlertHelper {

    public static void showAlert(Alert.AlertType alertType, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}