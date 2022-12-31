package agh.ics.oop.life;

import agh.ics.oop.Direction;
import agh.ics.oop.Vector2d;
import agh.ics.oop.gui.AbstractElement;
import agh.ics.oop.settings.BehaviourVariant;
import agh.ics.oop.settings.Settings;

import java.io.FileNotFoundException;
import java.util.Set;

public class Animal extends AbstractElement {
    Direction direction = Direction.random();
    int energy = 0;
    final int creationCycle;
    int childrenCount = 0;
    Genome genome;
    Settings settings;


    public Vector2d getPosition() {
        return position;
    }

    Vector2d position;


    public void setEnergy(int energy) {
        this.energy = energy;
        this.health = energy;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public Animal(int creationTurn, Vector2d position, Settings settings) throws FileNotFoundException {
        super("animal.png", 0);
        this.creationCycle = creationTurn;
        this.settings = settings;
        this.position = position;
        genome = new Genome(settings.getGenomeLength());
        setEnergy(settings.getReproductionEnergy() * 2);
    }

    public Animal(int creationTurn, Vector2d position, Settings settings, Genome genome)
    throws FileNotFoundException {
        super("animal.png", 0);
        this.creationCycle = creationTurn;
        this.settings = settings;
        this.position = position;
        this.genome = genome;
        setEnergy(settings.getReproductionEnergy() * 2);
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

    public int cmp(Animal other) {
        if (this.energy != other.energy)
            return this.energy - other.energy;
        else if (this.creationCycle != other.creationCycle)
            return this.creationCycle - other.creationCycle;
        else if (childrenCount != other.childrenCount)
            return this.childrenCount - other.childrenCount;
        else if (this.direction != other.direction)
            return this.direction.ordinal() - other.direction.ordinal();
        return 0;
    }

    public void eat() {
        energy += settings.getEnergyPerPlant();
    }

    public boolean isDead() {
        return energy <= 0;
    }

    public void rotate() {
        switch (settings.getBehaviourVariant()) {
            case PREDICTABLE -> genome.nextGene();
            case UNPREDICTABLE -> genome.setToRandom();
        }

        this.direction = this.direction.add(genome.getActiveGene());
    }

    public void move() {
        this.position = this.position.add(direction.toUnitVector());
    }

    public void comeBack() {
        this.direction = this.direction.opposite();
        move();
    }

    public void moveXModulo(Vector2d size) {
        this.position = new Vector2d((position.getX() + size.getX()) % size.getX(), position.getY());
    }

    public void teleport(Vector2d size) {
        this.position = Vector2d.random(size);
    }

    public void burnEnergy(int energy) {
        setEnergy(this.energy - energy);
    }

    public boolean isOnMap(Vector2d size) {
        return position.positiveAndBelow(size);
    }

    public Boolean canReproduce() {
        return energy >= settings.getFertileEnergy();
    }

    public Animal reproduce(Animal father, int turn) {
        int mothersGenes = (settings.getGenomeLength() * this.energy) / (this.energy + father.energy);

        Genome childGenome = genome.combine(father.genome, mothersGenes, settings);
        this.burnEnergy(settings.getReproductionEnergy());
        father.burnEnergy(settings.getReproductionEnergy());
        this.childrenCount++;
        father.childrenCount++;

        try {
            return new Animal(turn, this.position, settings, childGenome);
        } catch (FileNotFoundException e) {
            System.err.println("File not found in reproduce");
        }
        return null;
    }

    public int getEnergy() {
        return energy;
    }

    public int getAge(int turn) {
        return turn - creationCycle;
    }

    public Genome getGenome() {
        return genome;
    }
}
