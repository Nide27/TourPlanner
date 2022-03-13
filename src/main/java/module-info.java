module org.group07.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.group07.tourplanner to javafx.fxml;
    exports org.group07.tourplanner;
    exports org.group07.tourplanner.view;
    opens org.group07.tourplanner.view to javafx.fxml;
    exports org.group07.tourplanner.viewmodel;
    opens org.group07.tourplanner.viewmodel to javafx.fxml;
}