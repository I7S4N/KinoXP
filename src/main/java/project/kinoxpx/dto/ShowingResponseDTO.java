package project.kinoxpx.dto;

import java.time.LocalDateTime;

public record ShowingResponseDTO(
        Long id,
        String title,
        LocalDateTime startTime,
        String theaterType
) {}
