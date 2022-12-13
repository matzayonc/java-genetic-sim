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

    static Direction toDirection(int index) {
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

    Direction modify(int index) {
        int after = this.toIndex()+index%8;
        return Direction.toDirection(after);
    }
}