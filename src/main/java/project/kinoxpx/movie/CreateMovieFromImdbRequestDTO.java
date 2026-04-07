package project.kinoxpx.movie;

public record CreateMovieFromImdbRequestDTO(
        String imdbId,
        boolean is3d
) {
}
