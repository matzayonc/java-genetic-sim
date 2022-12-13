package agh.ics.oop.element;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AbstractElement {
    ImageView image;

    AbstractElement(String file) throws FileNotFoundException {
        Image imageImage = new Image(new FileInputStream("src/main/resources/" + file));
        image = new ImageView(imageImage);
    }
}
