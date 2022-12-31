package agh.ics.oop.map;

import agh.ics.oop.gui.AbstractElement;
import agh.ics.oop.Vector2d;
import agh.ics.oop.life.Animal;
import agh.ics.oop.life.Genome;
import agh.ics.oop.life.Plant;
import agh.ics.oop.settings.Settings;
import agh.ics.oop.stats.MapStats;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AbstractMap {
    protected Map<Vector2d, Field> entries = new HashMap<>();
    int turn = 0;
    MapStats stats = new MapStats();

    public AbstractMap(Settings settings){
        this.settings = settings;
    }

    public Vector2d getSize(){
        return settings.getMapSize();
    }
    Settings settings;

    public MapStats getStats(){
        stats.update(this);
        return stats;
    }

    public Field getField(Vector2d position){
        Field field = entries.get(position);
        if (field == null){
            field = new Field(position, this);
            entries.put(position, field);
        }
        return field;
    }

    public void addAnimal(Animal animal){
        this.getField(animal.getPosition()).animals.add(animal);
    }

    public void addPlant(Plant plant, Vector2d position){
        this.getField(position).plants.add(plant);
    }

    public Field getElement(Vector2d position){
        return entries.get(position);
    }

    public boolean isOccupied(Vector2d position){
        return entries.containsKey(position);
    }

    public int getWidth(){
        return settings.getMapSize().getX();
    }

    public int getHeight(){
        return settings.getMapSize().getY();
    }

    public int getTurn() {return turn;}
    public AbstractElement getToShow(Vector2d position){
        Field field = entries.get(position);
        if(field == null){
            return null;
        }
        return field.getToShow();
    }

    public int getAnimalCount() {
        return entries.values().stream().reduce(0, (sum, field) -> sum + field.animals.size(), Integer::sum);
    }

    public int getGrassCount() {
        return entries.values().stream().reduce(0, (sum, field) -> sum + field.plants.size(), Integer::sum);
    }

    public int getEmpty(){
        int nonEmpty = 0;
        for (Field field : entries.values())
            if(field.animals.isEmpty() || field.plants.isEmpty())
                nonEmpty++;

        int size = settings.getMapSize().getX() * settings.getMapSize().getY();
        return size - nonEmpty;
    }

    public int getTotalEnergy(){
        return entries.values().stream()
                .reduce(0, (sum, field) -> sum + field.animals.stream()
                        .reduce(0, (sum2, animal) -> sum2 + animal.getEnergy(), Integer::sum), Integer::sum);
    }

    public Genome getDominantGene() {
        Animal best = null;
        for(Field f : entries.values()){
            for(Animal a : f.animals){
                if(best == null || a.getChildrenCount() > best.getChildrenCount())
                    best = a;
            }
        }
        return best.getGenome();
    }

    public void tickMove() {
        List<Animal> moving = new LinkedList<>();
        for(Field f: entries.values()){
            while (!f.animals.isEmpty()){
                Animal animal = f.animals.get(0);
                f.animals.remove(0);
                moving.add(animal);
            }
            f.animals.clear();
        }
        moving.forEach(animal -> {
            animal.rotate();
            animal.move();
            if(!animal.isOnMap(settings.getMapSize()))
                handleExit(animal);
        });
        moving.forEach(animal -> getField(animal.getPosition()).animals.add(animal));
    }

    void handleExit(Animal animal) {
        switch (settings.getMapVariant()){
            case EARTH -> {
                if(animal.getPosition().getY() < 0 || animal.getPosition().getY() >= settings.getMapSize().getY()){
                    animal.comeBack();
                }
                animal.moveXModulo(settings.getMapSize());
            }
            case HELL -> {
                animal.burnEnergy(settings.getReproductionEnergy());
                animal.teleport(settings.getMapSize());
            }
        }
    }

    public void tick() {
        turn++;
        entries.values().forEach(Field::tickEnergy);
        entries.values().forEach(Field::tickDie);
        tickMove();
        entries.values().forEach(Field::tickEat);
        entries.values().forEach(Field::tickReproduce);
        tickGrow();
    }

    public void tickGrow() {
        for(int i = 0; i < settings.getPlantsPerCycle(); i++){
            Vector2d position = Vector2d.random(settings.getMapSize());

            switch (settings.getGrowthVariant()) {
                case EQUATOR -> {
                    int third = settings.getMapSize().getY() + 4 / 5;
                    int y = (int) (Math.random() * third);

                    // Likely case, only from among best fields
                    if(Math.random() > 0.8) {
                        position = new Vector2d((int) (Math.random() * settings.getMapSize().getX()), y + third);
                    } else if(Math.random() > 0.5) {
                        // Below equator
                        position = new Vector2d((int) (Math.random() * settings.getMapSize().getX()), y);
                    } else {
                        // Above equator
                        position = new Vector2d((int) (Math.random() * settings.getMapSize().getX()),
                                settings.getMapSize().getY() - 1 - y);
                    }
                }

                case TOXIC -> {
                    boolean onPreferred = Math.random() < 0.8;
                    int size = settings.getMapSize().getX() * settings.getMapSize().getY();
                    List<Vector2d> preferred = entries.values().stream()
                            .sorted((f1, f2) -> f2.deadAnimals - f1.deadAnimals)
                            .limit(size/5)
                            .map(f -> f.getPosition())
                            .collect(LinkedList::new, LinkedList::add, LinkedList::addAll);

                    while (preferred.contains(position) != onPreferred) {
                        position = Vector2d.random(settings.getMapSize());
                    }

                    try {
                        Plant plant = new Plant();
                        addPlant(plant, position); // allows for growth on top of other plants
                    } catch (IllegalArgumentException e) {
                        throw new RuntimeException(e);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            try {
                Plant plant = new Plant();
                addPlant(plant, position); // allows for growth on top of other plants
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int totalLifespan() {
        return entries.values().stream()
                .reduce(0, (sum, field) -> sum + field.totalTickLived, Integer::sum);
    }

    public int deadAnimals() {
        return entries.values().stream()
                .reduce(0, (sum, field) -> sum + field.deadAnimals, Integer::sum);
    }
}
