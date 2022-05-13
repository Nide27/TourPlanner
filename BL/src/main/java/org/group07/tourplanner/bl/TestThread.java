package org.group07.tourplanner.bl;

import javafx.beans.property.ObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class TestThread extends Thread {

    private ObjectProperty<Image> imgProperty;
    @SneakyThrows
    public TestThread(ObjectProperty<Image> imgProperty){
        this.imgProperty = imgProperty;
        InputStream stream = new FileInputStream("/Users/edinmuhovic/Documents/FH 4.Sem/SWE2/TourPlanner/App/src/main/resources/activity_indicator.gif");
        Image image = new Image(stream);
        imgProperty.set(image);
    }

    @SneakyThrows
    @Override
    public void run(){
        BufferedImage img = ImageIO.read(new URL("https://www.mapquestapi.com/staticmap/v5/map?session=627e742a-004d-993a-02b4-38dc-0a7902033dd3&size=500,500@2x&key=Zo9e7MdMG26Xb55t0Fusnyo75Fage2ib"));
        InputStream stream = new FileInputStream("/Users/edinmuhovic/Documents/FH 4.Sem/SWE2/TourPlanner/App/src/main/resources/static-map.jpeg");
        Image image = new Image(stream);
        //Image image2 = (Image)img;
        Image img2 = SwingFXUtils.toFXImage(img, null);
        imgProperty.set(img2);
    }
}
