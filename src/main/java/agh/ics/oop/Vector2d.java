package agh.ics.oop;

import java.util.Objects;

public class Vector2d {
    int x;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return x == vector2d.x && y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return this.x + "x" + this.y;
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public static Vector2d random(Vector2d limit) {
        return new Vector2d((int) (Math.random() * limit.x), (int) (Math.random() * limit.y));
    }

    public boolean positiveAndBelow(Vector2d limit){
        return this.x >= 0 && this.y >= 0 && this.x < limit.x && this.y < limit.y;
    }
}
