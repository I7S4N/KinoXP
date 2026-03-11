package project.kinoxpx.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.kinoxpx.dto.CreateMovieRequestDTO;
import project.kinoxpx.dto.MovieResponseDTO;
import project.kinoxpx.service.MovieService;

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

    @PostMapping
    public ResponseEntity<MovieResponseDTO> createMovie(@RequestBody CreateMovieRequestDTO req) {
        MovieResponseDTO response = movieService.createMovie(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping
    public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable Long id, @RequestBody CreateMovieRequestDTO req) {
        return ResponseEntity.ok(movieService.updateMovie(id, req));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}

