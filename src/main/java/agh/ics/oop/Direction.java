package agh.ics.oop;


public enum Direction {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    int getX() {
        return switch (this) {
            case WEST, NORTHWEST, SOUTHWEST -> -1;
            case EAST, NORTHEAST, SOUTHEAST -> 1;
            default -> 0;
        };
    }

    int getY() {
        return switch (this) {
            case NORTH, NORTHWEST, NORTHEAST -> 1;
            case SOUTH, SOUTHWEST, SOUTHEAST -> -1;
            default -> 0;
        };
    }

    public int toIndex() {
        return switch (this) {
            case NORTH -> 0;
            case NORTHEAST -> 1;
            case EAST -> 2;
            case SOUTHEAST -> 3;
            case SOUTH -> 4;
            case SOUTHWEST -> 5;
            case WEST -> 6;
            case NORTHWEST -> 7;
        };
    }

    public static Direction toDirection(int index) {
        return switch (index) {
            case 0 -> NORTH;
            case 1 -> NORTHEAST;
            case 2 -> EAST;
            case 3 -> SOUTHEAST;
            case 4 -> SOUTH;
            case 5 -> SOUTHWEST;
            case 6 -> WEST;
            case 7 -> NORTHWEST;
            default -> null;
        };
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
        return switch (this) {
            case NORTH -> SOUTH;
            case NORTHEAST -> SOUTHWEST;
            case EAST -> WEST;
            case SOUTHEAST -> NORTHWEST;
            case SOUTH -> NORTH;
            case SOUTHWEST -> NORTHEAST;
            case WEST -> EAST;
            case NORTHWEST -> SOUTHEAST;
        };
    }

    public Direction similar() {
        if(Math.random() > 0.5) {
            return this.add(Direction.NORTHEAST);
        } else {
            return this.add(Direction.NORTHWEST);
        }
    }
}