package agh.ics.oop.life;

import agh.ics.oop.Vector2d;
import agh.ics.oop.gui.AbstractElement;

import java.io.FileNotFoundException;

public class Plant extends AbstractElement {
    public Plant(Vector2d position) throws FileNotFoundException {
        super("grass.png", position);
    }
}
