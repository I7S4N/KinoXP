package project.kinoxpx.movie;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.kinoxpx.external.OmdbSearchItemDTO;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getAllMovies () {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> createMovie(@RequestBody CreateMovieRequestDTO req) {
        MovieResponseDTO response = movieService.createMovie(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<OmdbSearchItemDTO>> searchMovies(@RequestParam String title) {
        return ResponseEntity.ok(movieService.searchMovies(title));
    }

    @PostMapping("/from-imdb")
    public ResponseEntity<MovieResponseDTO> createMovieFromImdb(@RequestBody CreateMovieFromImdbRequestDTO req) {
        MovieResponseDTO response = movieService.createMovieFromImdb(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable Long id, @RequestBody CreateMovieRequestDTO req) {
        return ResponseEntity.ok(movieService.updateMovie(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}

