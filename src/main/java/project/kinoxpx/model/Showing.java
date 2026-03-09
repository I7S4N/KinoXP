package project.kinoxpx.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Showing {

    private Long id;
    private LocalDateTime startTime;

    private Movie movie;
    private Theater theater;

    private List<Reservation> reservations = new ArrayList<>();

    public Showing(Long id, LocalDateTime startTime, Movie movie, Theater theater) {
        this.id = id;
        this.startTime = startTime;
        this.movie = movie;
        this.theater = theater;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
}