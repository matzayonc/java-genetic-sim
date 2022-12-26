package agh.ics.oop.life;

import agh.ics.oop.Direction;
import agh.ics.oop.gui.AbstractElement;

import java.io.FileNotFoundException;

public class Animal extends AbstractElement {
    Direction direction = Direction.random();
    int energy = 0;
    final int creationCycle;
    int childrenCount = 0;

    public Animal(int creationTurn) throws FileNotFoundException {
        super("animal.png", 0);
        this.creationCycle = creationTurn;
    }

    public boolean hasPriority(Animal other) {
        if (this.energy != other.energy)
            return this.energy > other.energy;
        else if (this.creationCycle != other.creationCycle)
            return this.creationCycle < other.creationCycle;
        else if (childrenCount != other.childrenCount)
            return this.childrenCount > other.childrenCount;
        else if (this.direction != other.direction)
            return this.direction.ordinal() < other.direction.ordinal();
        return true;
    }
}
