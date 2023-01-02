package agh.ics.oop.settings;

public enum GrowthVariant {
    EQUATOR, TOXIC;

    public String toString() {
        return switch (this) {
            case EQUATOR -> "Equator";
            case TOXIC -> "Toxic";
        };
    }

    public static GrowthVariant fromString(String value) {
        return switch (value) {
            case "Equator" -> EQUATOR;
            case "Toxic" -> TOXIC;
            default -> throw new IllegalArgumentException("Unknown growth variant");
        };
    }

}
