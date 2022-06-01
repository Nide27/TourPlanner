package org.group07.tourplanner.app.viewmodel;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import lombok.Getter;

import org.group07.tourplanner.dal.DAL;
import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.dal.model.TourLog;

public class TourLogsViewModel {

    @Getter
    private ObservableList<TourLog> tourLogsList = FXCollections.observableArrayList();

    private final CreateTourLogViewModel createTourLogViewModel;
    private final EditTourLogViewModel editTourLogViewModel;

    private TourItem tourItem;

    public TourLogsViewModel(CreateTourLogViewModel createTourLogViewModel, EditTourLogViewModel editTourLogViewModel){
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

    public void setTourItem(TourItem tourItem){
        if(tourItem == null)
            return;

        this.tourItem = tourItem;

        setLogs(DAL.getInstance().getTourLogDao().getAllById(tourItem.getId()));
    }

    private void setLogs(List<TourLog> tourLogList){
        this.tourLogsList.clear();
        this.tourLogsList.addAll(tourLogList);
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
