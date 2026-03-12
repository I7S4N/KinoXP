package project.kinoxpx.service;

import project.kinoxpx.dto.ReservationRequestDTO;
import project.kinoxpx.dto.ReservationResponseDTO;
import project.kinoxpx.dto.SeatStatusResponseDTO;

public interface ReservationService {

    SeatStatusResponseDTO getSeatStatusResponse(Long showingId);

    ReservationResponseDTO createReservation(ReservationRequestDTO req);
}
