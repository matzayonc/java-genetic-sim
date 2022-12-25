package agh.ics.oop.settings;

public enum BehaviourVariant {
    PREDICTABLE, UNPREDICTABLE;

    public String toString() {
        return switch (this) {
            case PREDICTABLE -> "Predictable";
            case UNPREDICTABLE -> "Unpredictable";
        };
    }

    public static BehaviourVariant fromString(String str) throws IllegalArgumentException {
        switch (str) {
            case "Predictable":
                return PREDICTABLE;
            case "Unpredictable":
                return UNPREDICTABLE;
            default:
                throw new IllegalArgumentException("Unknown behaviour variant");
        }
    }
}
