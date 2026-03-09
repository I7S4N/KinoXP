package project.kinoxpx.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerInfo;

    @Column(nullable = false)
    private String ContactInfo;

    @Column(nullable = false)
    private int totalPrice;

    @Column(nullable = false)
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "showing_id", nullable = false)
    private Showing showing;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<ReservedSeats> seats;

    public Reservation(){}

    public Reservation(Long id, String customerInfo, String contactInfo, int totalPrice,
                       LocalDateTime localDateTime, Showing showing, List<ReservedSeats> seats) {
        this.id = id;
        this.customerInfo = customerInfo;
        ContactInfo = contactInfo;
        this.totalPrice = totalPrice;
        this.localDateTime = localDateTime;
        this.showing = showing;
        this.seats = seats;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public String getContactInfo() {
        return ContactInfo;
    }

    public void setContactInfo(String contactInfo) {
        ContactInfo = contactInfo;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public List<ReservedSeats> getSeats() {
        return seats;
    }

    public void setSeats(List<ReservedSeats> seats) {
        this.seats = seats;
    }
}
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double price) {
        this.totalPrice = price;
    }
}
