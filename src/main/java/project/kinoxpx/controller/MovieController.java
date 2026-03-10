package project.kinoxpx.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
