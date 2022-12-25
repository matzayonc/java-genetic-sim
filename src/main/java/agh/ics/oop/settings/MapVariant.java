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
        switch (str) {
            case "Earth":
                return EARTH;
            case "Hell":
                return HELL;
            default:
                throw new IllegalArgumentException("Unknown map variant");
        }
    }
}
