package project.kinoxpx.model;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private Long id;
    private String title;
    private int durationMinutes;
    private int ageLimit;
    private String category;
    private boolean is3D;

    private List<Showing> showings = new ArrayList<>();

    public Movie(Long id, String title, int durationMinutes, int ageLimit, String category, boolean is3D) {
        this.id = id;
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.ageLimit = ageLimit;
        this.category = category;
        this.is3D = is3D;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public String getCategory() {
        return category;
    }

    public boolean isIs3D() {
        return is3D;
    }

    public List<Showing> getShowings() {
        return showings;
    }

    public void addShowing(Showing showing) {
        showings.add(showing);
    }
}