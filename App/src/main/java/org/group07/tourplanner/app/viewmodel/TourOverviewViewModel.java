package org.group07.tourplanner.app.viewmodel;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.Button;
import lombok.Getter;
import org.group07.tourplanner.dal.DAL;
import org.group07.tourplanner.dal.model.TourItem;

import java.util.ArrayList;
import java.util.List;

public class TourOverviewViewModel {

    public interface SelectionChangedListener {
        void changeSelection(TourItem tourItem);
    }

    private List<SelectionChangedListener> listeners = new ArrayList<>();

    @Getter
    private ObservableList<TourItem> observableTourItems = FXCollections.observableArrayList();

    private final CreateTourViewModel createTourViewModel;

    public TourOverviewViewModel(CreateTourViewModel createTourViewModel) {
        setTours(DAL.getInstance().getTourItemDao().getAll());
        this.createTourViewModel = createTourViewModel;
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

    public void addNewTour(Button button) {

        //DAL.getInstance().getTourItemDao().create(tourItem);
        createTourViewModel.createWindow(button);
        //observableMediaItems.add(tour);
    }

    public void deleteTour(TourItem tourItem) {
        DAL.getInstance().getTourItemDao().delete(tourItem);
        observableTourItems.remove(tourItem);
    }
}
