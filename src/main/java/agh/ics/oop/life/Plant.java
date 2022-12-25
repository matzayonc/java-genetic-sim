package agh.ics.oop.life;

import agh.ics.oop.gui.AbstractElement;

import java.io.FileNotFoundException;

public class Plant extends AbstractElement {
    public Plant() throws FileNotFoundException {
        super("grass.png");
    }
}
