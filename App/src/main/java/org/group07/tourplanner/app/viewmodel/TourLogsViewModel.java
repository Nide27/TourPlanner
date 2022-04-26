package org.group07.tourplanner.app.viewmodel;

import javafx.beans.InvalidationListener;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import org.group07.tourplanner.dal.DAL;
import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.dal.model.TourLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TourLogsViewModel {

    private List<TourLog> tourLogs = new ArrayList<>();
    private volatile boolean isInitValue = false;

    //private final ListProperty<String> dates = new ListProperty<String>();
    private final List<StringProperty> comments = new ArrayList<>();
    private final List<StringProperty> difficulties = new ArrayList<>();
    private final List<FloatProperty> times = new ArrayList<>();
    private final List<IntegerProperty> rating = new ArrayList<>();


 /*   public TourLogsViewModel() {
        dates.addListener( (arg, oldVal, newVal)->updateLogModel());
    }

    private void updateLogModel() {
        if( !isInitValue )
            DAL.getInstance().tourItemDao().update(tourItemModel, Arrays.asList(tourItemModel.getId(), name.get(), distance.get(), estimate.get()));
    }
*/
    /*public interface SelectionChangedListener {
        void changeSelection(TourLog tourLog);
    }


private ObservableList<TourLog> observableLogItems = FXCollections.observableArrayList();

    public TourLogsViewModel() {
        setLogs( DAL.getInstance().tourLogDao().getAll() );
    }

    public void setLogs(List<TourLog> tourLog) {
        observableLogItems.clear();
        observableLogItems.addAll(tourLog);
    }


    private List<SelectionChangedListener> listeners = new ArrayList<>();

    private ObservableList<TourLog> observableMediaItems = FXCollections.observableArrayList();


    public ChangeListener<TourLog> getChangeListener() {
        return (observableValue, oldValue, newValue) -> notifyListeners(newValue);
    }

    public void addSelectionChangedListener(SelectionChangedListener listener) {
        listeners.add(listener);
    }

    public void removeSelectionChangedListener(SelectionChangedListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(TourLog newValue) {
        for ( var listener : listeners ) {
            listener.changeSelection(newValue);
        }
    }


    public void setLogs(List<TourLog> tourLog) {
        observableMediaItems.clear();
        observableMediaItems.addAll(tourLog);
    }*/



}
