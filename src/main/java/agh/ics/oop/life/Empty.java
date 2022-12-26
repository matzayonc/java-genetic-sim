package agh.ics.oop.life;

import agh.ics.oop.gui.AbstractElement;

import java.io.FileNotFoundException;

public class Empty extends AbstractElement {
    public Empty() throws FileNotFoundException {
        super(null, 0);
    }
}
