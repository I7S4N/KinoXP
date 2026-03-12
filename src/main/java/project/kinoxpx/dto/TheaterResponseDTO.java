package project.kinoxpx.dto;

public record TheaterResponseDTO(
        Long id,
        int numberOfRows,
        int seatsPerRow,
        String type) {
}