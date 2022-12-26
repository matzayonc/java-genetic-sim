package agh.ics.oop.gui;
import agh.ics.oop.Vector2d;
import agh.ics.oop.life.Animal;
import agh.ics.oop.life.Plant;
import agh.ics.oop.map.AbstractMap;
import agh.ics.oop.map.Earth;
import agh.ics.oop.settings.Settings;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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

        AbstractMap map = new Earth(settings.getMapSize());

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);

        Animal animal = new Animal(0);
        Animal anima2l = new Animal(5);
        Plant plant = new Plant();

        map.addElement(anima2l, new Vector2d(0, 0));
        map.addElement(animal, new Vector2d(0, 3));
        map.addElement(plant, new Vector2d(1, 2));

        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                VBox box = new VBox();
                box.setPrefSize(30, 30);

                AbstractElement getElement = map.getToShow(new Vector2d(i, j));
                if (getElement == null) {
                    box.getChildren().add(new Label("x"));
                } else {
                    System.out.println("here");
                    box.getChildren().add(getElement.getImage());
                }

                GridPane.setHalignment(box, HPos.CENTER);
                GridPane.setValignment(box, VPos.CENTER);
                grid.add(box, i, j);
            }
        }



            Scene scene = new Scene(grid, 400, 400);



        primaryStage.setScene(scene);
        primaryStage.show();
    }


    void refresh() {

    }
}
