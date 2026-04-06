package project.kinoxpx.showing;

import java.time.LocalDateTime;

public record CreateShowingRequestDTO(
        Long movieId,
        LocalDateTime startTime,
        Long theaterId
) {}
