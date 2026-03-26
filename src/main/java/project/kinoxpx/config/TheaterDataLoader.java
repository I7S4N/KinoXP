package project.kinoxpx.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.kinoxpx.theater.CreateTheaterRequestDTO;
import project.kinoxpx.theater.TheaterType;
import project.kinoxpx.theater.TheaterService;

@Component
public class TheaterDataLoader implements CommandLineRunner {

    private final TheaterService theaterService;

    public TheaterDataLoader(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @Override
    public void run(String... args) {
        if (theaterService.getAllTheaters().isEmpty()) {
            theaterService.createTheater(
                    new CreateTheaterRequestDTO(10, 12, TheaterType.SMALL)
            );

            theaterService.createTheater(
                    new CreateTheaterRequestDTO(15, 18, TheaterType.BIG)
            );
        }
    }
}