package project.kinoxpx.dto;

public record MovieResponseDTO(
        Long id,
        String title,
        int year,
        int durationMin,
        int ageLimit,
        String category,
        boolean is3d) {
}
