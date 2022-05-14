package org.group07.tourplanner.app.viewmodel;

import javafx.beans.property.*;
import lombok.Getter;
import lombok.SneakyThrows;
import org.group07.tourplanner.bl.MapQuestThread;
import org.group07.tourplanner.dal.ConfigManager;
import org.group07.tourplanner.dal.model.TourItem;

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
    private final ObjectProperty<javafx.scene.image.Image> imageView = new SimpleObjectProperty<>();

    public TourDetailsViewModel() {
        name.addListener( (arg, oldVal, newVal)->updateTourModel());
    }

    @SneakyThrows
    public void setTourModel(TourItem tourItem){

        ResourceBundle res = ResourceBundle.getBundle("org.group07.tourplanner.app." + "gui_strings", ConfigManager.getInstance().getLocale());

        this.tourItem = tourItem;
        name.setValue(tourItem.getName());
        description.setValue(res.getString("DETAIL_DESCRIPTION") + tourItem.getDescription());
        departure.setValue(tourItem.getDeparture());
        destination.setValue(tourItem.getDestination());
        transport.setValue(tourItem.getTransport());
        distance.setValue(res.getString("DETAIL_DISTANCE") + tourItem.getDistance());
        estimate.setValue(res.getString("DETAIL_ESTIMATE") + tourItem.getEstimate());

        MapQuestThread mapQuestThread = new MapQuestThread(tourItem, imageView, distance, estimate);
        mapQuestThread.start();
    }

    private void updateTourModel() {
        //DAL.getInstance().getTourItemDao().update(tourItemModel, Arrays.asList(tourItemModel.getId(), name.get(), distance.get(), estimate.get()));
    }
}
