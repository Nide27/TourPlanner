package org.group07.tourplanner.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.group07.tourplanner.view.TopBarController;

public class MainWindowViewModel {

    private TopBarViewModel topBarViewModel;


    public MainWindowViewModel(TopBarViewModel topBarViewModel) {
        this.topBarViewModel = topBarViewModel;

    }


}
