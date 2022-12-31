package agh.ics.oop.gui;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AbstractElement {
    ImageView image;
    int displayPriority = 0;
    protected int health = 0;


    protected AbstractElement(String file, int displayPriority) throws FileNotFoundException {
        Image imageImage = new Image(new FileInputStream("src/main/resources/" + file));
        this.displayPriority = displayPriority;
        image = new ImageView(imageImage);
        image.setFitWidth(30);
        image.setFitHeight(30);
    }

    public Node getImage() {
        StackPane stackPane = new StackPane();
        VBox box = new VBox();
        box.getChildren().add(image);

        if (health > 0) {
            Line line = new Line(0, 0, health < 29 ? health : 29, 0);
            line.setStrokeWidth(2);
            line.setStyle("-fx-stroke: green;");
            box.getChildren().add(line);
        }
        return box;
    }

}
