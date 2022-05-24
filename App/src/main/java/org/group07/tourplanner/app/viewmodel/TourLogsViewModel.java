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
import lombok.Getter;
import org.group07.tourplanner.dal.DAL;
import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.dal.model.TourLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TourLogsViewModel {

    @Getter
    private ObservableList<TourLog> tourLogsList = FXCollections.observableArrayList();

    private final CreateTourLogViewModel createTourLogViewModel;
    private final EditTourLogViewModel editTourLogViewModel;

    private TourItem tourItem;
    private List<TourLog> tourLogs = new ArrayList<>();
    private volatile boolean isInitValue = false;

    //private final ListProperty<String> dates = new ListProperty<String>();
    private final List<StringProperty> comments = new ArrayList<>();
    private final List<StringProperty> difficulties = new ArrayList<>();
    private final List<FloatProperty> times = new ArrayList<>();
    private final List<IntegerProperty> rating = new ArrayList<>();

    public TourLogsViewModel(CreateTourLogViewModel createTourLogViewModel, EditTourLogViewModel editTourLogViewModel){
        //setLogs(DAL.getInstance().getTourLogDao().getAllById(tourItemModel.getId()));
        this.createTourLogViewModel = createTourLogViewModel;
        this.editTourLogViewModel = editTourLogViewModel;
    }

    public interface SelectionChangedListener {
        void changeSelection(TourLog tourLog);
    }

    private List<TourLogsViewModel.SelectionChangedListener> listeners = new ArrayList<>();

    public ChangeListener<TourLog> getChangeListener() {
        return (observableValue, oldValue, newValue) -> notifyListeners(newValue);
    }

    private void notifyListeners(TourLog newValue) {
        for ( var listener : listeners ) {
            listener.changeSelection(newValue);
        }
    }

    public void setTourModel(TourItem tourItemModel){
        if(tourItemModel == null)
            return;

        this.tourItem = tourItemModel;

        setLogs(DAL.getInstance().getTourLogDao().getAllById(tourItemModel.getId()));

    }

    private void setLogs(List<TourLog> tourLogList){
        this.tourLogs = tourLogList;

        this.tourLogsList.clear();
        this.tourLogsList.addAll(tourLogList);

        System.out.println(tourLogList.size());
    }

    public void createTourLog(){
        if(tourItem == null)
            return;

        this.createTourLogViewModel.createWindow(this.tourLogsList, tourItem);
    }

    public void deleteTourLog(TourLog tourLog){
        if(tourLog == null)
            return;

        DAL.getInstance().getTourLogDao().delete(tourLog);
        tourLogsList.remove(tourLog);
    }

    public void editTourLog(TourLog tourLog){
        if(tourLog == null)
            return;

        this.editTourLogViewModel.createWindow(tourLogsList, tourLog);
    }
}
