package agh.ics.oop.map;

import agh.ics.oop.Vector2d;
import agh.ics.oop.gui.AbstractElement;

import java.util.LinkedList;
import java.util.List;

public class Field {
    private final Vector2d position;
    private final AbstractMap map;

    public List<AbstractElement> elements = new LinkedList<>();

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
        if(elements.isEmpty()){
            return null;
        }
        AbstractElement best = elements.get(0);

        for(AbstractElement element : elements){
            if(element.getDisplayPriority() < best.getDisplayPriority()){
                best = element;
            }
        }
        return best;
    }
}
