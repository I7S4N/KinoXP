package project.kinoxpx.service;

import project.kinoxpx.model.ReservedSeats;
import project.kinoxpx.model.Showing;

import java.util.List;

public interface PricingService {
    int calculateTotalPrice(Showing showing, List<ReservedSeats> seats);
}