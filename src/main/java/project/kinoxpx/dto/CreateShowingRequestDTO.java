package project.kinoxpx.dto;

import java.time.LocalDateTime;

public record CreateShowingRequestDTO(
        Long movieId,
        Long theaterId,
        LocalDateTime startTime) {
}
