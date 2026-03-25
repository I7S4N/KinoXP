package project.kinoxpx.showing;

import jakarta.persistence.*;
import project.kinoxpx.theater.Theater;
import project.kinoxpx.movie.Movie;

import java.time.LocalDateTime;

@Entity
public class Showing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Theater theater;

    public Showing(){}

    public Showing(Long id, LocalDateTime startTime, Movie movie, Theater theater) {
        this.id = id;
        this.startTime = startTime;
        this.movie = movie;
        this.theater = theater;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }
}
