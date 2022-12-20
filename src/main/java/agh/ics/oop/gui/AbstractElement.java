package agh.ics.oop.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AbstractElement {
    ImageView image;

    protected AbstractElement(String file) throws FileNotFoundException {
        Image imageImage = new Image(new FileInputStream("src/main/resources/" + file));
        image = new ImageView(imageImage);
    }
}
