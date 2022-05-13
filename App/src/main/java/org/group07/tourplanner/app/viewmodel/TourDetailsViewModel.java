package org.group07.tourplanner.app.viewmodel;

import javafx.beans.property.*;
import javafx.embed.swing.SwingFXUtils;
import lombok.Getter;
import lombok.SneakyThrows;
import org.group07.tourplanner.bl.TestThread;
import org.group07.tourplanner.dal.model.TourItem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;

public class TourDetailsViewModel {
    private TourItem tourItemModel;
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
    private final DoubleProperty distance = new SimpleDoubleProperty();
    @Getter
    private final StringProperty estimate = new SimpleStringProperty();
    @Getter
    private final ObjectProperty<javafx.scene.image.Image> imageView = new SimpleObjectProperty<>();

    public TourDetailsViewModel() {
        name.addListener( (arg, oldVal, newVal)->updateTourModel());
    }

    @SneakyThrows
    public void setTourModel(TourItem tourItemModel){
        isInitValue = true;
        if( tourItemModel ==null ) {
            // select the first in the list
            name.set("");
            description.set("");
            departure.set("");
            destination.set("");
            transport.set("");
            distance.set(0);
            estimate.set("");
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

        TestThread testThread = new TestThread(imageView);
        testThread.start();

        /*BufferedImage img = ImageIO.read(new URL("https://www.mapquestapi.com/staticmap/v5/map?session=627e742a-004d-993a-02b4-38dc-0a7902033dd3&size=500,500@2x&key=Zo9e7MdMG26Xb55t0Fusnyo75Fage2ib"));
        InputStream stream = new FileInputStream("/Users/edinmuhovic/Documents/FH 4.Sem/SWE2/TourPlanner/App/src/main/resources/static-map.jpeg");
        Image image = new Image(stream);
        //Image image2 = (Image)img;
        Image img2 = SwingFXUtils.toFXImage(img, null);
        imageView.set(img2);*/


        isInitValue = false;
    }

    private void updateTourModel() {
        if( !isInitValue ){

        }
            //DAL.getInstance().getTourItemDao().update(tourItemModel, Arrays.asList(tourItemModel.getId(), name.get(), distance.get(), estimate.get()));
    }
}
