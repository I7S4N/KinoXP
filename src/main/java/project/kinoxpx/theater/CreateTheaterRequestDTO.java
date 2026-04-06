package project.kinoxpx.theater;

public record CreateTheaterRequestDTO(
        int numberOfRows,
        int seatsPerRow,
        TheaterType type
) {}