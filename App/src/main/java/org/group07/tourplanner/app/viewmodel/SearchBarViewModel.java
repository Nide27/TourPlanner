package org.group07.tourplanner.app.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class SearchBarViewModel {
    public interface SearchListener {
        void search(String searchString);
    }

    private List<SearchListener> listeners = new ArrayList<>();

    private final StringProperty searchString = new SimpleStringProperty("");

    public SearchBarViewModel() {  }

    public StringProperty searchStringProperty() {
        return searchString;
    }

    public void addSearchListener(SearchListener listener) {
        listeners.add(listener);
    }

    public void doSearch() {
        for (var listener : listeners ) {
            listener.search(searchString.get());
        }
    }
}
