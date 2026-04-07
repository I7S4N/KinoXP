package project.kinoxpx.movie;

import project.kinoxpx.external.OmdbSearchItemDTO;
import java.util.List;

public interface MovieService {
    //ved at implementere metoderne her, så gøre det,
    // det mere overskuelig at vide hvad sevice laget skal bruge
    // og hvad vores controler kan kalde på

    MovieResponseDTO createMovie(CreateMovieRequestDTO req);
    List<MovieResponseDTO> getAllMovies();
    MovieResponseDTO updateMovie(Long id, CreateMovieRequestDTO req);
    void deleteMovie(Long id);
    MovieResponseDTO getMovieById(Long id);

    List<OmdbSearchItemDTO> searchMovies(String title);
    MovieResponseDTO createMovieFromImdb(CreateMovieFromImdbRequestDTO req);
}
