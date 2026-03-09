package project.kinoxpx.model;

public class ReservedSeat {

    private int rowNumber;
    private int seatNumber;

    public ReservedSeat(int rowNumber, int seatNumber) {
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}