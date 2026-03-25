package project.kinoxpx.reservation;

import project.kinoxpx.seat.SeatDTO;

import java.util.List;

public record ReservationRequestDTO(
        Long showingId,
        String customerName,
        String contactInfo,
        List<SeatDTO> seats
) {}
