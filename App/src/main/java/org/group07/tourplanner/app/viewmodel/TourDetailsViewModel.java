package org.group07.tourplanner.app.viewmodel;

import javafx.beans.property.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

import lombok.Getter;

import org.group07.tourplanner.bl.ResourceManager;
import org.group07.tourplanner.bl.MapQuestThread;
import org.group07.tourplanner.dal.TourLogDao;
import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.dal.model.TourLog;

public class TourDetailsViewModel {

    private TourItem tourItem;

    private final ResourceManager rm;

    private final TourLogDao tourLogDao;

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

    public TourDetailsViewModel(TourLogDao tourLogDao) {
        this.rm = ResourceManager.getInstance();
        this.tourLogDao = tourLogDao;
    }

    public void setTourItem(TourItem tourItem) throws SQLException, IOException {

        this.tourItem = tourItem;

        if(this.tourItem == null)
            return;

        name.setValue(tourItem.getName());
        description.setValue(rm.load("DETAIL_DESCRIPTION_HEADER") + tourItem.getDescription());
        departure.setValue(tourItem.getDeparture());
        destination.setValue(tourItem.getDestination());
        transport.setValue(tourItem.getTransport());
        distance.setValue(rm.load("DETAIL_DISTANCE_HEADER") + tourItem.getDistance() + " " + rm.load("DETAIL_DISTANCE_UNIT"));
        estimate.setValue(rm.load("DETAIL_ESTIMATE_HEADER") + tourItem.getEstimate());

        MapQuestThread mapQuestThread = new MapQuestThread(tourItem, imageView, distance, estimate);
        mapQuestThread.start();

        double totalRating = 0;
        double totalDifficulty = 0;

        List<TourLog> tourLogList = tourLogDao.getAllById(tourItem.getId());

        ListIterator<TourLog> it = tourLogList.listIterator();

        while(it.hasNext()){
            TourLog tourLog = it.next();

            totalRating += tourLog.getRating();
            totalDifficulty += tourLog.getDifficulty();
        }

        double avgRating = totalRating;
        double avgDifficulty = totalDifficulty;

        if(tourLogList.size() > 0){
            avgRating = totalRating / tourLogList.size();
            avgDifficulty = totalDifficulty / tourLogList.size();
        }

        popularity.setValue(rm.load("DETAIL_POPULARITY_HEADER") + avgRating * tourLogList.size());

        if(avgDifficulty == 0)
            childFriendliness.setValue(rm.load("DETAIL_N/A"));
        else if(avgDifficulty < 4)
            childFriendliness.setValue(rm.load("DETAIL_CHILD_FRIENDLY_HIGH"));
        else if(avgDifficulty > 6)
            childFriendliness.setValue(rm.load("DETAIL_CHILD_FRIENDLY_LOW"));
        else
            childFriendliness.set(rm.load("DETAIL_CHILD_FRIENDLY_MID"));
    }
}
