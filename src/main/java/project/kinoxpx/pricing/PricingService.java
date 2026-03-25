package project.kinoxpx.pricing;

import project.kinoxpx.seat.ReservedSeats;
import project.kinoxpx.showing.Showing;

import java.util.List;

public interface PricingService {
    int calculateTotalPrice(Showing showing, List<ReservedSeats> seats);
}