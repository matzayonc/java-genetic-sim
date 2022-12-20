package agh.ics.oop.gui;
import agh.ics.oop.Vector2d;
import agh.ics.oop.map.AbstractMap;
import agh.ics.oop.map.Earth;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class App extends Application {
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("Zwierzak");

        AbstractMap map = new Earth(new Vector2d(4, 5));

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.add(label, 0, 0);
        grid.add(new Label("Zwierzak"), 5, 3);

        Scene scene = new Scene(grid, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void refresh() {

    }
}
