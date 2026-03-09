package project.kinoxpx.model;

public enum TheaterType {
    SMALL("SMALL CINEMA"),
    BIG("BIG CINEMA");


    private final String displayName;

    TheaterType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
