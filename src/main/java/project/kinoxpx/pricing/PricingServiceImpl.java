package project.kinoxpx.pricing;

import org.springframework.stereotype.Service;
import project.kinoxpx.seat.ReservedSeats;
import project.kinoxpx.showing.Showing;

import java.util.List;

@Service
public class PricingServiceImpl implements PricingService {

    private static final int BASE_PRICE = 95;

    @Override
    public int calculateTotalPrice(Showing showing, List<ReservedSeats> seats) {
        return BASE_PRICE * seats.size();
    }
}