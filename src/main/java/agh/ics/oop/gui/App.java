package agh.ics.oop.gui;
import agh.ics.oop.Engine;
import agh.ics.oop.Vector2d;
import agh.ics.oop.life.Animal;
import agh.ics.oop.map.AbstractMap;
import agh.ics.oop.map.Earth;
import agh.ics.oop.settings.Settings;
import agh.ics.oop.stats.MapStats;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


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
        VBox box = new VBox();
        grid.setGridLinesVisible(true);


        for(int i = 0; i < settings.getStartAnimalCount(); i++)
            map.addAnimal(new Animal(0, Vector2d.random(settings.getMapSize()), settings));

        box.setPadding(new Insets(10));


        primaryStage.setScene(new Scene(box, 400, 400));
        primaryStage.show();

        Runnable task = () -> {
            System.out.println("Animals: " + map.getAnimalCount());
            refresh(box, grid, map);
        };
        engine.add(task, map);
    }


    void refresh(VBox mainBox, GridPane grid, AbstractMap map) {
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
                    box.getChildren().add(getElement.getImage());
                }

                GridPane.setHalignment(box, HPos.CENTER);
                GridPane.setValignment(box, VPos.CENTER);
                grid.add(box, i, j);
            }
        }

        MapStats stats = map.getStats();
        VBox box = new VBox();
        box.getChildren().add(new Label("Animals: " + stats.getAnimalCount()));
        box.getChildren().add(new Label("Grass: " + stats.getGrassCount()));
        box.getChildren().add(new Label("Dominant gene: " + stats.getDominatingGene()));
        box.getChildren().add(new Label("freeFields: " + stats.getFreeFieldsCount()));
        box.getChildren().add(new Label("Average energy: " + stats.getAvgEnergy()));
        box.getChildren().add(new Label("Average age: " + stats.getAvgLifespan()));

        mainBox.getChildren().clear();
        mainBox.getChildren().add(grid);
        mainBox.getChildren().add(box);
    }
}
