package service;

import dto.CreateMovieRequest;
import dto.MovieResponse;
import model.Movie;
import org.springframework.stereotype.Service;
import repository.MovieRepository;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    //Constructøren injection af movieRepository
    //Gør det muligt at hente og gemme Movie entities i databasen
    public MovieServiceImpl(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }


    @Override
    public MovieResponse createMovie(CreateMovieRequest req) {
        // Mapper data fra CreateMovieRequest (DTO fra klienten) til Movie entity
        Movie movie = new Movie();
        movie.setTitle(req.getTitle());
        movie.setYear(req.getYear());
        movie.setCategory(req.getCategory());
        movie.setDurationMin(req.getDurationMin());
        movie.setAgeLimit(req.getAgeLimit());
        movie.setIs3d(req.isIs3d());

        //Gemmer Movie entity i databasen viaMovieRepository
        movie = movieRepository.save(movie);


        //Mapper den gemte Movie Entity til movieResponse DTO
        //DTO bruges til at sende data tilbage til klienten
        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getYear(),
                movie.getDurationMin(),
                movie.getAgeLimit(),
                movie.getCategory(),
                movie.isIs3d()
        );
    }

    @Override
    public List<MovieResponse> getAllMovies() {

        // Henter alle film fra databasen
        // Mapper hver Movie entity til MovieResponse DTO
        // Returnerer en liste af DTO’er til klienten
        return movieRepository.findAll().stream().map(movie -> new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getYear(),
                movie.getDurationMin(),
                movie.getAgeLimit(),
                movie.getCategory(),
                movie.isIs3d()
        ))
                .toList();
    }
}
