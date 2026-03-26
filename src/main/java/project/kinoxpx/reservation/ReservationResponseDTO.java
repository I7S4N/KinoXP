package project.kinoxpx.reservation;

public record ReservationResponseDTO(
        Long reservationId,
        Integer totalPrice,
        String message
) {}
