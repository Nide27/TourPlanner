module org.group07.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.group07.tourplanner to javafx.fxml;
    exports org.group07.tourplanner;
}