package project.kinoxpx.reservation;

import project.kinoxpx.seat.SeatStatusResponseDTO;

public interface ReservationService {

    SeatStatusResponseDTO getSeatStatusResponse(Long showingId);

    ReservationResponseDTO createReservation(ReservationRequestDTO req);
}
