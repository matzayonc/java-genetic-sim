package agh.ics.oop;

public class Vector2d {
    int x;
    int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
