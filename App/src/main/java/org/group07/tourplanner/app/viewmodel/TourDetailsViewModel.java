package org.group07.tourplanner.app.viewmodel;

import javafx.beans.property.*;
import org.group07.tourplanner.dal.model.TourItem;

public class TourDetailsViewModel {
    private TourItem tourItemModel;
    private volatile boolean isInitValue = false;

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty departure = new SimpleStringProperty();
    private final StringProperty destination = new SimpleStringProperty();
    private final StringProperty transport = new SimpleStringProperty();
    private final FloatProperty distance = new SimpleFloatProperty();
    private final FloatProperty estimate = new SimpleFloatProperty();

    public TourDetailsViewModel() {
        name.addListener( (arg, oldVal, newVal)->updateTourModel());
    }



    public String getName() {
        return name.get();
    }
    public StringProperty nameProperty() {
        return name;
    }

    public String getDescription() {
        return description.get();
    }
    public StringProperty descriptionProperty() {
        return description;
    }

    public String getFrom() {
        return departure.get();
    }
    public StringProperty fromProperty() {
        return departure;
    }

    public String getTo() {
        return destination.get();
    }
    public StringProperty toProperty() {
        return destination;
    }

    public String getTransport() {
        return transport.get();
    }
    public StringProperty transportProperty() {
        return transport;
    }

    public double getDistance() {
        return distance.get();
    }
    public FloatProperty distanceProperty() {
        return distance;
    }

    public float getEstimate() {
        return estimate.get();
    }
    public FloatProperty estimateProperty() {
        return estimate;
    }

    public void setTourModel(TourItem tourItemModel) {
        isInitValue = true;
        if( tourItemModel ==null ) {
            // select the first in the list
            name.set("");
            description.set("");
            departure.set("");
            destination.set("");
            transport.set("");
            distance.set(0);
            estimate.set(0);
            return;
        }
        this.tourItemModel = tourItemModel;
        name.setValue( tourItemModel.getName() );
        description.setValue( tourItemModel.getDescription() );
        departure.setValue( tourItemModel.getDeparture() );
        destination.setValue( tourItemModel.getDestination() );
        transport.setValue( tourItemModel.getTransport() );
        distance.set( tourItemModel.getDistance() );
        estimate.set( tourItemModel.getEstimate() );
        isInitValue = false;
    }

    private void updateTourModel() {
        if( !isInitValue ){

        }
            //DAL.getInstance().getTourItemDao().update(tourItemModel, Arrays.asList(tourItemModel.getId(), name.get(), distance.get(), estimate.get()));
    }
}
