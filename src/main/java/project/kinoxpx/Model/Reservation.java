package project.kinoxpx.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reservation {

    private Long id;
    private String customerName;
    private String contact;
    private double totalPrice;
    private LocalDateTime createdAt;

    private Showing showing;

    private List<ReservedSeat> seats = new ArrayList<>();

    public Reservation(Long id, String customerName, String contact, Showing showing) {
        this.id = id;
        this.customerName = customerName;
        this.contact = contact;
        this.showing = showing;
        this.createdAt = LocalDateTime.now();
    }

    public void addSeat(int row, int seat) {
        seats.add(new ReservedSeat(row, seat));
    }

    public List<ReservedSeat> getSeats() {
        return seats;
    }

    public Showing getShowing() {
        return showing;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double price) {
        this.totalPrice = price;
    }
}