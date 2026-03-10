package project.kinoxpx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.kinoxpx.service.MovieService;

@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController (MovieService movieService) {
        this.movieService = movieService;
    }

}
