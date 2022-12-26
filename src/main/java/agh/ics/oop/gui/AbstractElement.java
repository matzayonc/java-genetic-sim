package agh.ics.oop.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AbstractElement {
    ImageView image;
    int displayPriority = 0;


    protected AbstractElement(String file, int displayPriority) throws FileNotFoundException {
        Image imageImage = new Image(new FileInputStream("src/main/resources/" + file));
        this.displayPriority = displayPriority;
        image = new ImageView(imageImage);
        image.setFitWidth(30);
        image.setFitHeight(30);
    }

    public ImageView getImage() {
        return image;
    }

    public int getDisplayPriority() {
        return displayPriority;
    }
}
