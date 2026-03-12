package project.kinoxpx.dto;

public record MovieResponseDTO(
        Long id,
        String title,
        int movieYear,
        int durationMin,
        String rated,
        String category,
        boolean is3d) {
}
