package org.group07.tourplanner.app.viewmodel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.group07.tourplanner.dal.DAL;
import org.group07.tourplanner.dal.model.TourItem;

public class TourOverviewViewModel {

    public interface SelectionChangedListener {
        void changeSelection(TourItem tourItem);
    }

    private List<SelectionChangedListener> listeners = new ArrayList<>();

    private static final Logger logger = LogManager.getLogger(DAL.class);

    @Getter
    private ObservableList<TourItem> observableTourItems = FXCollections.observableArrayList();

    private final CreateTourItemViewModel createTourItemViewModel;
    private final EditTourItemViewModel editTourItemViewModel;

    public TourOverviewViewModel(CreateTourItemViewModel createTourItemViewModel, EditTourItemViewModel editTourItemViewModel){
        try {
            setTours(DAL.getInstance().getTourItemDao().getAll());
        } catch (SQLException e) {
            logger.warn("Could not retrieve TourItems:\n" + e);
        }
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

    public void createTour() throws IOException {
        createTourItemViewModel.createWindow(observableTourItems);
    }

    public void deleteTour(TourItem tourItem) throws SQLException {
        if(tourItem == null)
            return;

        DAL.getInstance().getTourItemDao().delete(tourItem);
        observableTourItems.remove(tourItem);
    }

    public void updateTour(TourItem tourItem) throws SQLException, IOException {
        if(tourItem == null)
            return;

        editTourItemViewModel.createWindow(observableTourItems, tourItem);

        Optional<TourItem> editedTourItem = DAL.getInstance().getTourItemDao().get(tourItem.getId());

        notifyListeners(editedTourItem.get());
    }
}
