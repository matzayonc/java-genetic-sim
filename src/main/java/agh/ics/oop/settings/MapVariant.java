package agh.ics.oop.settings;

public enum MapVariant {
    EARTH, HELL;

    public String toString() {
        return switch (this) {
            case EARTH -> "Earth";
            case HELL -> "Hell";
        };
    }

    public static MapVariant fromString(String str) throws IllegalArgumentException {
        return switch (str) {
            case "Earth" -> EARTH;
            case "Hell" -> HELL;
            default -> throw new IllegalArgumentException("Unknown map variant");
        };
    }
}
