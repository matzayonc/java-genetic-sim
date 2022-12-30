package agh.ics.oop.map;

import agh.ics.oop.Vector2d;
import agh.ics.oop.gui.AbstractElement;
import agh.ics.oop.life.Animal;
import agh.ics.oop.life.Plant;

import java.util.LinkedList;
import java.util.List;

public class Field {
    private final Vector2d position;
    private final AbstractMap map;

    public int deadAnimals = 0;
    public int alivePlants = 0;

    public List<Animal> animals = new LinkedList<>();
    public List<Plant> plants = new LinkedList<>();

    public Field(Vector2d position, AbstractMap map) {
        this.position = position;
        this.map = map;
    }

    public Vector2d getPosition() {
        return position;
    }

    public AbstractMap getMap() {
        return map;
    }

    public AbstractElement getToShow(){
        if(!animals.isEmpty())
            return animals.get(0);
        else if (!plants.isEmpty())
            return plants.get(0);
        else
            return null;
    }

    public void tickDie(){
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).isDead()) {
                deadAnimals++;
                animals.remove(i);
                i--;
            }
        }
    }

    public void tickEat() {
        animals.sort(Animal::cmp);

        while (!animals.isEmpty() && !plants.isEmpty()) {
            animals.get(0).eat();
            plants.remove(0);
            alivePlants -= 1;
        }
    }

    public void tickReproduce() {
        // Took constraint of only one child per turn per animal
        List<Animal> readyToReproduce = new LinkedList<Animal>();
        animals.stream().filter(Animal::canReproduce).forEach(readyToReproduce::add);
        readyToReproduce.sort(Animal::cmp);

        while (readyToReproduce.size() >= 2) {
            Animal mother = readyToReproduce.remove(0);
            Animal father = readyToReproduce.remove(0);
            Animal child;

            if(Math.random() > 0.5)
                child = mother.reproduce(father, map.getTurn());
            else
                child = father.reproduce(mother, map.getTurn());

            if(child != null)
                animals.add(child);
        }
    }

    public void tickEnergy() {
        animals.forEach(a -> a.burnEnergy(1));
    }
}
