package org.group07.tourplanner;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainViewModel {

    private final StringProperty searchResult = new SimpleStringProperty("");
    private final StringProperty testLabel = new SimpleStringProperty("Test");

    public void saveValue(){
        System.out.println(searchResult.get());
        testLabel.setValue(searchResult.get());
        searchResult.setValue("");
    }

    public StringProperty getSearchResult() {
        return searchResult;
    }

    public StringProperty setLabel(){
        return testLabel;
    }
}
