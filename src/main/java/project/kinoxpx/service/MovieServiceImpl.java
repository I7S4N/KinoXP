package project.kinoxpx.service;

import project.kinoxpx.dto.CreateMovieRequestDTO;
import project.kinoxpx.dto.MovieResponseDTO;
import project.kinoxpx.exception.InvalidRequestException;
import project.kinoxpx.exception.ResourceNotFoundException;
import project.kinoxpx.external.OmdbMovieDTO;
import project.kinoxpx.external.OmdbService;
import project.kinoxpx.model.Movie;
import org.springframework.stereotype.Service;
import project.kinoxpx.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final OmdbService omdbService;

    private final MovieRepository movieRepository;

    //Constructøren injection af movieRepository
    //Gør det muligt at hente og gemme Movie entities i databasen
    public MovieServiceImpl(OmdbService omdbService, MovieRepository movieRepository){
        this.omdbService = omdbService;
        this.movieRepository = movieRepository;
    }

    // Mapper Movie entity til MovieResponseDTO
    private MovieResponseDTO mapToDTO(Movie movie) {
        return new MovieResponseDTO(
                movie.getId(),
                movie.getTitle(),
                movie.isIs3d()
        );
    }

    @Override
    public MovieResponseDTO createMovie(CreateMovieRequestDTO req) {

        // ---------- Validering -----------------
        if (req.title() == null || req.title().isBlank()) {
            throw new InvalidRequestException("Title cannot be empty");
        }

        // TODO: tilføj exceptions

        // TODO: #1 hvis år ikke matcher med api'en

        // TODO: #2 hvis titlen ikke findes

        // ---------- Check om filmen allerede findes i databasen
        Optional<Movie> existingMovie = movieRepository.findByTitleIgnoreCase(req.title());

        if (existingMovie.isPresent()) {
            // Hvis filmen allerede findes returnerer vi den eksisterende
            return mapToDTO(existingMovie.get());
        }



        // Henter vi film fra OMDB API
        OmdbMovieDTO omdbMovie = omdbService.fetchMovieByTitle(req.title());

        if (omdbMovie == null || omdbMovie.title == null) {
            throw new ResourceNotFoundException("Movie not found in OMDB");
        }






        // Mapper data fra CreateMovieRequest (DTO fra klienten) til Movie entity
        Movie movie = new Movie();


        // Titel fra OMDB
        movie.setTitle(omdbMovie.title);


        // OMDB year er String til parse til int
        movie.setMovieYear(Integer.parseInt(omdbMovie.year));


        // Runtime fx "148 min"
        String runtime = omdbMovie.runtime.replace(" min","");
        movie.setDurationMin(Integer.parseInt(runtime));


        // Genre fra OMDB
        movie.setCategory(omdbMovie.genre);

        // Rated (ageLimit) fra OMDB
        movie.setRated(omdbMovie.rated);

        // Standard værdier
        movie.setRated(omdbMovie.rated);
        movie.setIs3d(req.is3d());


        //Gemmer Movie entity i databasen viaMovieRepository
        movie = movieRepository.save(movie);



        //Mapper den gemte Movie Entity til movieResponse DTO
        //DTO bruges til at sende data tilbage til klienten
        return new MovieResponseDTO(
                movie.getId(),
                movie.getTitle(),
                movie.isIs3d()
        );
    }

    @Override
    public List<MovieResponseDTO> getAllMovies() {

        // Henter alle film fra databasen
        // Mapper hver Movie entity til MovieResponse DTO
        // Returnerer en liste af DTO’er til klienten
        return movieRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public MovieResponseDTO updateMovie(Long id, CreateMovieRequestDTO req) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with ID " + id + " was not found"));

        if (req.title() == null || req.title().isBlank()) {
            throw new InvalidRequestException("Title cannot be empty");
        }

        movie.setTitle(req.title());
        movie.setIs3d(req.is3d());

        return mapToDTO(movieRepository.save(movie));
    }

    @Override
    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new ResourceNotFoundException("Movie with ID " + id + " was not found");
        }
        movieRepository.deleteById(id);
    }
}


