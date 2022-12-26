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

        Label label = new Label("Select settings from preset");



        GridPane grid = new GridPane();
        grid.setGridLinesVisible(false);
        grid.add(label, 0, 0);

        Scene scene = new Scene(grid, 300, 400);

        int row = 3;

        for(String preset : Settings.list()) {

            Button button = new Button(preset);
            button.setOnAction(ev -> {
                try {
                    System.out.println(Settings.load(preset).toString());
                    this.open(preset);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            button.setAlignment(javafx.geometry.Pos.CENTER);
            grid.add(button, 0, row++);
        }


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void open(String preset) throws IOException {
        Stage primaryStage = new Stage();
        Settings settings = Settings.load(preset);

        Label label = new Label("Zwierzak");

        AbstractMap map = new Earth(new Vector2d(4, 5));

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.add(label, 0, 0);
        grid.add(new Label("Zwierzak"), 5, 3);

        Scene scene = new Scene(grid, 400, 400);

        Animal animal = new Animal(0);


        primaryStage.setScene(scene);
        primaryStage.show();    }

    void refresh() {

    }
}
