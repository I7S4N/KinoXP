package project.kinoxpx.dto;

import project.kinoxpx.model.TheaterType;

public record CreateTheaterRequestDTO(
        int numberOfRows,
        int seatsPerRow,
        TheaterType type
) {}