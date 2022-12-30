package agh.ics.oop.map;

import agh.ics.oop.gui.AbstractElement;
import agh.ics.oop.Vector2d;
import agh.ics.oop.life.Animal;
import agh.ics.oop.life.Plant;
import agh.ics.oop.settings.Settings;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AbstractMap {
    protected Map<Vector2d, Field> entities = new HashMap<>();

    int turn = 0;

    public AbstractMap(Settings settings){
        this.settings = settings;
    }

    public Vector2d getSize(){
        return settings.getMapSize();
    }
    Settings settings;

    public Field getField(Vector2d position){
        Field field = entities.get(position);
        if (field == null){
            field = new Field(position, this);
            entities.put(position, field);
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
        return entities.get(position);
    }

    public boolean isOccupied(Vector2d position){
        return entities.containsKey(position);
    }

    public int getWidth(){
        return settings.getMapSize().getX();
    }

    public int getHeight(){
        return settings.getMapSize().getY();
    }

    public int getTurn() {return turn;}
    public AbstractElement getToShow(Vector2d position){
        Field field = entities.get(position);
        if(field == null){
            return null;
        }
        return field.getToShow();
    }

    public int getAnimalCount() {
        return entities.values().stream().reduce(0, (sum, field) -> sum + field.animals.size(), Integer::sum);
    }

    public void tickMove() {
        List<Animal> moving = new LinkedList<>();
        for(Field f: entities.values()){
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
        entities.values().forEach(Field::tickEnergy);
        entities.values().forEach(Field::tickDie);
        tickMove();
        entities.values().forEach(Field::tickEat);
        entities.values().forEach(Field::tickReproduce);
    }
}
