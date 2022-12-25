package agh.ics.oop.settings;

public enum GrowthVariant {
    EQUATOR, TOXIC;

    public String toString() {
        return switch (this) {
            case EQUATOR -> "Equator";
            case TOXIC -> "Equator";
        };
    }

    public static GrowthVariant fromString(String value) {
        switch (value) {
            case "Equator":
                return EQUATOR;
            case "Toxic":
                return TOXIC;
            default:
                throw new IllegalArgumentException("Unknown growth variant");
        }
    }

}
