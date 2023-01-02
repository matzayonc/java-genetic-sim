package agh.ics.oop.gui;

import agh.ics.oop.Vector2d;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AbstractElement {
    ImageView image;
    protected int health = 0;
    final private Vector2d position;

    public Vector2d getPosition() {
        return position;
    }

    protected AbstractElement(String file, Vector2d position) throws FileNotFoundException {
        Image imageImage = new Image(new FileInputStream("src/main/resources/" + file));
        image = new ImageView(imageImage);
        image.setFitWidth(30);
        image.setFitHeight(30);
        this.position = position;
    }

    public Node getImage() {
        VBox box = new VBox();
        box.getChildren().add(image);

        if (health > 0) {
            Line line = new Line(0, 0, Math.min(health,29), 0);
            line.setStrokeWidth(2);
            line.setStyle("-fx-stroke: green;");
            box.getChildren().add(line);
        }
        return box;
    }

}
