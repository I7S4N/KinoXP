package model;

import jakarta.persistence.*;

@Entity

@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 4)
    private int year;

    @Column(nullable = false)
    private int durationMin;

    private int ageLimit;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private boolean is3d;

    public Movie(Long id, String title, int year, int durationMin, int ageLimit, String category, boolean is3d) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.durationMin = durationMin;
        this.ageLimit = ageLimit;
        this.category = category;
        this.is3d = is3d;
    }

    public Movie(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isIs3d() {
        return is3d;
    }

    public void setIs3d(boolean is3d) {
        this.is3d = is3d;
    }
}
