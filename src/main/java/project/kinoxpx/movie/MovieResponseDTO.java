package project.kinoxpx.movie;

public record MovieResponseDTO(
        Long id,
        String title,
        Integer movieYear,
        Integer durationMin,
        String rated,
        String category,
        boolean is3d) {
}
