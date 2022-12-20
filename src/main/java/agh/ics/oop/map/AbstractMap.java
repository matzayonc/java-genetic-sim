package agh.ics.oop.map;

import agh.ics.oop.gui.AbstractElement;
import agh.ics.oop.Vector2d;

import java.util.HashMap;
import java.util.Map;

public class AbstractMap {
    protected Vector2d size ;
    protected Map<Vector2d, AbstractElement> entities = new HashMap<>();

    public AbstractMap(Vector2d size){
        this.size = size;
    }

    public Vector2d getSize(){
        return size;
    }

    public void addElement(AbstractElement element, Vector2d position){
        entities.put(position, element);
    }

    public AbstractElement getElement(Vector2d position){
        return entities.get(position);
    }

    public boolean isOccupied(Vector2d position){
        return entities.containsKey(position);
    }
}
