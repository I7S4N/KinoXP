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
                movie.getMovieYear(),
                movie.getDurationMin(),
                movie.getRated(),
                movie.getCategory(),
                movie.getIs3d()
        );
    }

    @Override
    public MovieResponseDTO getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with ID: " + id + "was not found"));

        return mapToDTO(movie);
    }

    @Override
    public MovieResponseDTO createMovie(CreateMovieRequestDTO req) {

        // ---------- Validering -----------------
        if (req.title() == null || req.title().isBlank()) {
            throw new InvalidRequestException("Title cannot be empty");
        }

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

        // OMDB year er String til parse til int, lavet i metode
        movie.setMovieYear(parseYear(omdbMovie.year));

        // Runtime fx "148 min"
        movie.setDurationMin(parseRuntime(omdbMovie.runtime));

        // Genre fra OMDB
        movie.setCategory(omdbMovie.genre);

        // Standard værdier
        movie.setRated(omdbMovie.rated); // agelimit "PG-xx"
        movie.setIs3d(req.is3d());


        //Gemmer Movie entity i databasen viaMovieRepository
        movie = movieRepository.save(movie);


        //Mapper den gemte Movie Entity til movieResponse DTO
        //DTO bruges til at sende data tilbage til klienten

        // måske slette 104 - 112
//        return new MovieResponseDTO(
//                movie.getId(),
//                movie.getTitle(),
//                movie.getMovieYear(),
//                movie.getDurationMin(),
//                movie.getRated(),
//                movie.getCategory(),
//                movie.getIs3d()
//        );

        return mapToDTO(movie);
    }

    // fjerner alle mellemrum og ekstra tal udover de fire første cifre
    private int parseYear(String year) {
        try {
            String cleaned = year.replaceAll("[^0-9]", "");
            if (cleaned.length() < 4) {
                throw new InvalidRequestException("Invalid year format");
            }
            return Integer.parseInt(cleaned.substring(0, 4));
        } catch (Exception e) {
            throw new InvalidRequestException("Invalid year format");
        }
    }

    // sikrer ordenligt format, når admin indtaster varighed på film
    private int parseRuntime(String runtime) {
        try {
            String cleaned = runtime.replaceAll("[^0-9]", "");
            if (cleaned.isBlank()) {
                throw new InvalidRequestException("Invalid runtime format");
            }
            return Integer.parseInt(cleaned);
        } catch (Exception e) {
            throw new InvalidRequestException("Invalid runtime format");
        }
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


