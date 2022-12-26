package agh.ics.oop.map;

import agh.ics.oop.gui.AbstractElement;
import agh.ics.oop.Vector2d;

import java.util.HashMap;
import java.util.Map;

public class AbstractMap {
    protected Vector2d size ;
    protected Map<Vector2d, Field> entities = new HashMap<>();

    public AbstractMap(Vector2d size){
        this.size = size;
    }

    public Vector2d getSize(){
        return size;
    }

    public void addElement(AbstractElement element, Vector2d position){
        Field field = entities.get(position);
        if(field == null){
            field = new Field(position, this);
            entities.put(position, field);
        }

        field.elements.add(element);
    }

    public Field getElement(Vector2d position){
        return entities.get(position);
    }

    public boolean isOccupied(Vector2d position){
        return entities.containsKey(position);
    }

    public int getWidth(){
        return size.getX();
    }

    public int getHeight(){
        return size.getY();
    }

    public AbstractElement getToShow(Vector2d position){
        Field field = entities.get(position);
        if(field == null){
            return null;
        }
        return field.getToShow();
    }
}
