package org.group07.tourplanner.app.viewmodel;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.group07.tourplanner.dal.DAL;
import org.group07.tourplanner.dal.model.TourItem;

import java.util.ArrayList;
import java.util.List;

public class TourOverviewViewModel {
    public interface SelectionChangedListener {
        void changeSelection(TourItem tourItem);
    }

    private List<SelectionChangedListener> listeners = new ArrayList<>();

    private ObservableList<TourItem> observableMediaItems = FXCollections.observableArrayList();

    public TourOverviewViewModel() {
        setTours(DAL.getInstance().getTourItemDao().getAll());
    }

    public ObservableList<TourItem> getObservableTours() {
        return observableMediaItems;
    }

    public ChangeListener<TourItem> getChangeListener() {
        return (observableValue, oldValue, newValue) -> notifyListeners(newValue);
    }

    public void addSelectionChangedListener(SelectionChangedListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(TourItem newValue) {
        for ( var listener : listeners ) {
            listener.changeSelection(newValue);
        }
    }

    public void setTours(List<TourItem> tourItems) {
        observableTourItems.clear();
        observableTourItems.addAll(tourItems);
    }

    public void addNewTour(TourItem tourItem) {

        DAL.getInstance().getTourItemDao().create(tourItem);
        //observableMediaItems.add(tour);
    }

    public void deleteTour(TourItem tourItem) {
        DAL.getInstance().getTourItemDao().delete(tourItem);
        observableTourItems.remove(tourItem);
    }
}
