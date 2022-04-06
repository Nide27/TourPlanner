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
        void changeSelection(TourItem mediaItem);
    }

    private List<SelectionChangedListener> listeners = new ArrayList<>();

    private ObservableList<TourItem> observableMediaItems = FXCollections.observableArrayList();

    public TourOverviewViewModel()
    {
        setTours( DAL.getInstance().tourDao().getAll() );
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

    public void removeSelectionChangedListener(SelectionChangedListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(TourItem newValue) {
        for ( var listener : listeners ) {
            listener.changeSelection(newValue);
        }
    }

    public void setTours(List<TourItem> mediaItems) {
        observableMediaItems.clear();
        observableMediaItems.addAll(mediaItems);
    }

    public void addNewTour() {
        var tour = DAL.getInstance().tourDao().create();
        observableMediaItems.add(tour);
    }

    public void deleteTour(TourItem mediaItem) {
        DAL.getInstance().tourDao().delete(mediaItem);
        observableMediaItems.remove(mediaItem);
    }
}
