package project.kinoxpx.dto;

public record CreateMovieRequestDTO(
        String title,
        int movieYear,
        int durationMin,
        String rated,
        String category,
        boolean is3d) {
}
