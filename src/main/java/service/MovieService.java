package service;

import dto.CreateMovieRequest;
import dto.MovieResponse;

import java.util.List;

public interface MovieService {
    //ved at implementere metoderne her, så gøre det,
    // det mere overskuelig at vide hvad sevice laget skal bruge
    // og hvad vores controler kan kalde på

    MovieResponse createMovie(CreateMovieRequest req);
    List<MovieResponse> getAllMovies();
}
