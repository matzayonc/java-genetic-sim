package agh.ics.oop.gui;
import agh.ics.oop.Engine;
import agh.ics.oop.Vector2d;
import agh.ics.oop.life.Animal;
import agh.ics.oop.life.Plant;
import agh.ics.oop.map.AbstractMap;
import agh.ics.oop.map.Earth;
import agh.ics.oop.settings.Settings;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;


public class App extends Application {

    public static Engine engine = new Engine();

    public void start(Stage primaryStage) throws Exception {

        Label label = new Label("Select settings from preset");

        VBox box = new VBox();
        box.setSpacing(10);
        box.setPadding(new Insets(10));
        box.getChildren().add(label);

        Scene scene = new Scene(box, 300, 400);

        Engine.app = this;

        for(String preset : Settings.list()) {

            Button button = new Button(preset);

            button.setOnAction(ev -> {
                try {
                     this.open(preset);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            button.setAlignment(javafx.geometry.Pos.CENTER);
            box.getChildren().add(button);
        }


        primaryStage.setScene(scene);
        primaryStage.show();

        Thread engineThread = new Thread(engine::run);
        engineThread.start();
    }

    public void update(Runnable task){
        Platform.runLater(task);
    }

    public void open(String preset) throws IOException, InterruptedException {
        Stage primaryStage = new Stage();
        Settings settings = Settings.load(preset);

        AbstractMap map = new Earth(settings);

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);


        Animal animal = new Animal(0, new Vector2d(0, 0), settings);
        Animal anima2l = new Animal(5, new Vector2d(0, 3), settings);
        Plant plant = new Plant();

        map.addAnimal(anima2l);
        map.addAnimal(animal);
        map.addPlant(plant, new Vector2d(1, 2));

        primaryStage.setScene(new Scene(grid, 400, 400));
        primaryStage.show();

        int c = 3;

//        while (c-- > 0) {
//            System.out.println("Animals: " + map.getAnimalCount());
//
//            refresh(grid, map);
//            grid.setGridLinesVisible(true);
//
//            Thread.sleep(5000);
//        }
        grid.setGridLinesVisible(true);

        Runnable callableTask = () -> {
            System.out.println("Animals: " + map.getAnimalCount());
            refresh(grid, map);
        };

        engine.add(callableTask, map);
    }


    void refresh(GridPane grid, AbstractMap map) {
        grid.getChildren().clear();
        grid.setGridLinesVisible(true);

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
    }
}
