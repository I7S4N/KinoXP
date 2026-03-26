package project.kinoxpx.exceptions;

public class SeatAlreadyReservedException extends RuntimeException {
    public SeatAlreadyReservedException(String message) {
        super(message);
    }
}
