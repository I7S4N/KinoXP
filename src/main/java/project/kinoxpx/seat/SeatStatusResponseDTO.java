package project.kinoxpx.seat;

import java.util.List;

public record SeatStatusResponseDTO(
        int numberOfRows,
        int seatsPerRow,
        List<SeatDTO> reservedSeats
) {}
