package project.kinoxpx.dto;

import project.kinoxpx.model.TheaterType;

public record TheaterResponseDTO(Long id, int numberOfRows, int seatsPerRow, String type) {
}