package agh.ics.oop.gui;
import agh.ics.oop.Vector2d;
import agh.ics.oop.life.Animal;
import agh.ics.oop.map.AbstractMap;
import agh.ics.oop.map.Earth;
import agh.ics.oop.settings.Settings;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {

    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("Zwierzak");

        AbstractMap map = new Earth(new Vector2d(4, 5));

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.add(label, 0, 0);
        grid.add(new Label("Zwierzak"), 5, 3);

        Scene scene = new Scene(grid, 400, 400);

        Animal animal = new Animal(0);

        for(String preset : Settings.list()) {
            System.out.println(preset);

            Button button = new Button(preset);
            button.setOnAction(ev -> {
                try {
                    System.out.println(Settings.load(preset).toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            grid.add(button, 0, 0);
        }




        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void refresh() {

    }
}
