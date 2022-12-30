package agh.ics.oop;

import java.security.PublicKey;

public enum Direction {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    Vector2d toVector() {
        return new Vector2d(this.getX(), this.getY());
    }

    int getX() {
        switch (this) {
            case WEST:
            case NORTHWEST:
            case SOUTHWEST:
                return -1;
            case EAST:
            case NORTHEAST:
            case SOUTHEAST:
                return 1;
            default:
                return 0;
        }
    }

    int getY() {
        switch (this) {
            case NORTH:
            case NORTHWEST:
            case NORTHEAST:
                return 1;
            case SOUTH:
            case SOUTHWEST:
            case SOUTHEAST:
                return -1;
            default:
                return 0;
        }
    }

    int toIndex() {
        switch (this) {
            case NORTH:
                return 0;
            case NORTHEAST:
                return 1;
            case EAST:
                return 2;
            case SOUTHEAST:
                return 3;
            case SOUTH:
                return 4;
            case SOUTHWEST:
                return 5;
            case WEST:
                return 6;
            case NORTHWEST:
                return 7;
            default:
                return -1;
        }
    }

    public static Direction toDirection(int index) {
        switch (index) {
            case 0:
                return NORTH;
            case 1:
                return NORTHEAST;
            case 2:
                return EAST;
            case 3:
                return SOUTHEAST;
            case 4:
                return SOUTH;
            case 5:
                return SOUTHWEST;
            case 6:
                return WEST;
            case 7:
                return NORTHWEST;
            default:
                return null;
        }
    }
    public Direction add(Direction direction) {
        int after = (this.toIndex() + direction.toIndex()) % 8;
        return Direction.toDirection(after);
    }

    public static Direction random() {
        return Direction.toDirection((int)(Math.random()*8));
    }

    public Vector2d toUnitVector() {
        return new Vector2d(this.getX(), this.getY());
    }

    public Direction opposite() {
        switch (this) {
            case NORTH:
                return SOUTH;
            case NORTHEAST:
                return SOUTHWEST;
            case EAST:
                return WEST;
            case SOUTHEAST:
                return NORTHWEST;
            case SOUTH:
                return NORTH;
            case SOUTHWEST:
                return NORTHEAST;
            case WEST:
                return EAST;
            case NORTHWEST:
                return SOUTHEAST;
            default:
                return null;
        }
    }

    public Direction similar() {
        if(Math.random() > 0.5) {
            return this.add(Direction.NORTHEAST);
        } else {
            return this.add(Direction.NORTHWEST);
        }
    }
}