package project.kinoxpx.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import project.kinoxpx.model.Movie;
import project.kinoxpx.repository.MovieRepository;
import project.kinoxpx.repository.ShowingRepository;

import java.util.List;

@Configuration
public class InitData implements CommandLineRunner {
    private final MovieRepository movieRepository;
    private final ShowingRepository showingRepository;

    public InitData(MovieRepository movieRepository, ShowingRepository showingRepository) {
        this.movieRepository = movieRepository;
        this.showingRepository = showingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Movie m1 = new Movie(null, "Inception", 2010, 148, 13, "Sci-Fi", false);
        Movie m2 = new Movie(null, "Avatar", 2009, 162, 13, "Adventure", true);
        Movie m3 = new Movie(null, "The Lion King", 1994, 88, 7, "Animation", false);
        Movie m4 = new Movie(null, "The Dark Knight", 2008, 152, 15, "Action", false);
        Movie m5 = new Movie(null, "Frozen", 2013, 102, 7, "Animation", true);

        movieRepository.saveAll(List.of(
                m1, m2, m3, m4, m5
        ));


    }


}

