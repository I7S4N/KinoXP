package project.kinoxpx.theater;

public record TheaterResponseDTO(
        Long id,
        int numberOfRows,
        int seatsPerRow,
        String type
) {}