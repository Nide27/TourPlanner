package org.group07.tourplanner.app.viewmodel;

import javafx.beans.property.*;
import lombok.Getter;
import lombok.SneakyThrows;
import org.group07.tourplanner.bl.MapQuestThread;
import org.group07.tourplanner.dal.ConfigManager;
import org.group07.tourplanner.dal.DAL;
import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.dal.model.TourLog;

import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

public class TourDetailsViewModel {

    private TourItem tourItem;
    private volatile boolean isInitValue = false;

    @Getter
    private final StringProperty name = new SimpleStringProperty();
    @Getter
    private final StringProperty description = new SimpleStringProperty();
    @Getter
    private final StringProperty departure = new SimpleStringProperty();
    @Getter
    private final StringProperty destination = new SimpleStringProperty();
    @Getter
    private final StringProperty transport = new SimpleStringProperty();
    @Getter
    private final StringProperty distance = new SimpleStringProperty();
    @Getter
    private final StringProperty estimate = new SimpleStringProperty();
    @Getter
    private final StringProperty popularity = new SimpleStringProperty();
    @Getter
    private final StringProperty childFriendliness = new SimpleStringProperty();
    @Getter
    private final ObjectProperty<javafx.scene.image.Image> imageView = new SimpleObjectProperty<>();

    public TourDetailsViewModel() {
        name.addListener( (arg, oldVal, newVal)->updateTourModel());
    }

    @SneakyThrows
    public void setTourModel(TourItem tourItem){

        ResourceBundle res = ResourceBundle.getBundle("org.group07.tourplanner.app." + "gui_strings", ConfigManager.getInstance().getLocale());

        this.tourItem = tourItem;

        if(this.tourItem == null)
            return;

        name.setValue(tourItem.getName());
        description.setValue(res.getString("DETAIL_DESCRIPTION") + tourItem.getDescription());
        departure.setValue(tourItem.getDeparture());
        destination.setValue(tourItem.getDestination());
        transport.setValue(tourItem.getTransport());
        distance.setValue(res.getString("DETAIL_DISTANCE") + tourItem.getDistance() + " km");
        estimate.setValue(res.getString("DETAIL_ESTIMATE") + tourItem.getEstimate());

        int totalRating = 0;
        int totalDifficulty = 0;

        List<TourLog> tourLogList = DAL.getInstance().getTourLogDao().getAllById(tourItem.getId());

        ListIterator<TourLog> it = tourLogList.listIterator();

        while(it.hasNext()){
            TourLog tourLog = it.next();

            totalRating += tourLog.getRating();
            totalDifficulty += tourLog.getDifficulty();
        }

        int avgRating = totalRating;
        int avgDifficulty = totalDifficulty;

        if(tourLogList.size() > 0){
            avgRating = totalRating / tourLogList.size();
            avgDifficulty = totalDifficulty / tourLogList.size();
        }

        //Popularity = # of logs * average rating
        popularity.setValue(res.getString("DETAIL_POPULARITY") + avgRating * tourLogList.size());

        double childFriendlinessIndicator = avgDifficulty * tourItem.getDistance();

        switch(transport.getValue()){
            case "WALKING":
                childFriendlinessIndicator /= 3; break;

            case "CYCLING":
                childFriendlinessIndicator /= 5; break;

            case "DRIVING":
                childFriendlinessIndicator /= 100; break;
        }

        if(childFriendlinessIndicator < 4)
            childFriendliness.setValue(res.getString("DETAIL_CHILDFRIENDLY_VERY"));
        else if(childFriendlinessIndicator > 6)
            childFriendliness.setValue(res.getString("DETAIL_CHILDFRIENDLY_NOT"));
        else
            childFriendliness.set(res.getString("DETAIL_CHILDFRIENDLY_OK"));

        MapQuestThread mapQuestThread = new MapQuestThread(tourItem, imageView, distance, estimate);
        mapQuestThread.start();
    }

    private void updateTourModel() {
        //DAL.getInstance().getTourItemDao().update(tourItemModel, Arrays.asList(tourItemModel.getId(), name.get(), distance.get(), estimate.get()));
    }
}
