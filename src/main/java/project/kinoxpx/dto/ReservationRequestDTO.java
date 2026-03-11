package project.kinoxpx.dto;

import java.util.List;

public record ReservationRequestDTO(Long showingId,
                                    String customerName,
                                    String contactInfo,
                                    List<SeatDTO> seats) {
}
