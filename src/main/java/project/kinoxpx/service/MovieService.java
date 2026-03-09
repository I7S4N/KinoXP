package project.kinoxpx.service;

import org.springframework.stereotype.Service;
import project.kinoxpx.dto.CreateMovieRequest;
import project.kinoxpx.dto.MovieResponse;

import java.util.List;

@Service
public interface MovieService {
    //ved at implementere metoderne her, så gøre det,
    // det mere overskuelig at vide hvad sevice laget skal bruge
    // og hvad vores controler kan kalde på

    MovieResponse createMovie(CreateMovieRequest req);
    List<MovieResponse> getAllMovies();
}
