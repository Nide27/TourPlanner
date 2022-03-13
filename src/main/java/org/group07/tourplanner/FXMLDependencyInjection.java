package org.group07.tourplanner;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import org.group07.tourplanner.view.ControllerFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class FXMLDependencyInjection {

    public static Parent load(String location, Locale locale) throws IOException{
        System.out.println(locale + "  " + location);
        FXMLLoader loader = getLoader(location, locale);
        return loader.load();
    }

    public static FXMLLoader getLoader(String location, Locale locale){
        return new FXMLLoader(
                FXMLDependencyInjection.class.getResource("/org/group07/tourplanner/" + location),
                ResourceBundle.getBundle("org.group07.tourplanner." + "gui_strings", locale),
                new JavaFXBuilderFactory(),
                controllerClass-> ControllerFactory.getInstance().create(controllerClass)
        );
    }
}
