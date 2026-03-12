package project.kinoxpx.model;

import jakarta.persistence.*;

@Entity
public class ReservedSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer seatRowNumber;

    @Column(nullable = false)
    private Integer seatNumber;


    @ManyToOne
    @JoinColumn(name = "showing_id", nullable = false)
    private Showing showing;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    public ReservedSeats() {}

    public ReservedSeats(Long id, Integer seatRowNumber, Integer seatNumber, Reservation reservation, Showing showing) {
        this.id = id;
        this.seatRowNumber = seatRowNumber;
        this.seatNumber = seatNumber;
        this.reservation = reservation;
        this.showing = showing;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeatRowNumber() {
        return seatRowNumber;
    }

    public void setSeatRowNumber(Integer rowNumber) {
        this.seatRowNumber = rowNumber;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }
}
