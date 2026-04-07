package project.kinoxpx.movie;

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
    private Integer movieYear;

    @Column(nullable = false)
    private Integer durationMin;

    private String rated;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private boolean is3d;

    @Column(unique = true)
    private String imdbId;

    public Movie(Long id, String title, Integer movieYear, Integer durationMin, String rated, String category, boolean is3d) {
        this.id = id;
        this.title = title;
        this.movieYear = movieYear;
        this.durationMin = durationMin;
        this.rated = rated;
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

    public Integer getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(Integer year) {
        this.movieYear = year;
    }

    public Integer getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(Integer durationMin) {
        this.durationMin = durationMin;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getIs3d() {
        return is3d;
    }

    public void setIs3d(boolean is3d) {
        this.is3d = is3d;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }
}
