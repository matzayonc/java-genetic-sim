package agh.ics.oop.settings;

public enum MutationVariant {
    FULL_VARIABILITY, SLIGHT_CORRECTION;

    public String toString() {
        return switch (this) {
            case FULL_VARIABILITY -> "Full variability";
            case SLIGHT_CORRECTION -> "Slight correction";
        };
    }

    public static MutationVariant fromString(String str) throws IllegalArgumentException {
        return switch (str) {
            case "Full variability" -> FULL_VARIABILITY;
            case "Slight correction" -> SLIGHT_CORRECTION;
            default -> throw new IllegalArgumentException("Unknown mutation variant");
        };
    }

}
