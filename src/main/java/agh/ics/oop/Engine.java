package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Platform;

import agh.ics.oop.map.AbstractMap;
import java.util.LinkedList;
import java.util.List;

public class Engine implements Runnable{
    List<Runnable> refreshes = new LinkedList<>();
    List<AbstractMap> maps = new LinkedList<>();
    public static App app;

    public Engine() {
//        this.tasks.add(this::run);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            maps.stream().forEach(AbstractMap::tick);

            for (Runnable task : refreshes) {
                Platform.runLater(task);
            }
        }
    }

    public void add(Runnable task, AbstractMap map){
        refreshes.add(task);
        maps.add(map);
    }

    public int count(){
        return refreshes.size();
    }
}
