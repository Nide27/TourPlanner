package org.group07.tourplanner.app.viewmodel;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import lombok.Getter;
import org.group07.tourplanner.dal.DAL;
import org.group07.tourplanner.dal.model.TourItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourOverviewViewModel {

    public interface SelectionChangedListener {
        void changeSelection(TourItem tourItem);
    }

    private List<SelectionChangedListener> listeners = new ArrayList<>();

    @Getter
    private ObservableList<TourItem> observableTourItems = FXCollections.observableArrayList();

    private final CreateTourItemViewModel createTourItemViewModel;
    private final EditTourItemViewModel editTourItemViewModel;

    public TourOverviewViewModel(CreateTourItemViewModel createTourItemViewModel, EditTourItemViewModel editTourItemViewModel) {
        setTours(DAL.getInstance().getTourItemDao().getAll());
        this.createTourItemViewModel = createTourItemViewModel;
        this.editTourItemViewModel = editTourItemViewModel;
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

    public void addNewTour() {

        //DAL.getInstance().getTourItemDao().create(tourItem);
        createTourItemViewModel.createWindow(observableTourItems);
        //observableMediaItems.add(tour);
    }

    public void deleteTour(TourItem tourItem) {
        if(tourItem == null)
            return;

        DAL.getInstance().getTourItemDao().delete(tourItem);
        observableTourItems.remove(tourItem);
    }

    public void updateTour(TourItem tourItem){
        if(tourItem == null)
            return;

        editTourItemViewModel.createWindow(observableTourItems, tourItem);

        Optional<TourItem> editedTourItem = DAL.getInstance().getTourItemDao().get(tourItem.getId());

        notifyListeners(editedTourItem.get());
    }
}
