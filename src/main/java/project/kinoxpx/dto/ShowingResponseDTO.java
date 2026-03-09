package project.kinoxpx.dto;

import java.time.LocalDateTime;

public record ShowingResponseDTO(
        Long id,
        String movieTitle,
        LocalDateTime startTime,
        String theaterType) {
}
