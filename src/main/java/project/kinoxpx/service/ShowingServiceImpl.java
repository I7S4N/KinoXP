package project.kinoxpx.service;

import org.springframework.stereotype.Service;
import project.kinoxpx.dto.CreateShowingRequestDTO;
import project.kinoxpx.dto.ShowingResponseDTO;
import project.kinoxpx.model.Movie;
import project.kinoxpx.model.Showing;
import project.kinoxpx.model.Theater;
import project.kinoxpx.repository.MovieRepository;
import project.kinoxpx.repository.ShowingRepository;
import project.kinoxpx.repository.TheaterRepository;

import java.util.List;

@Service
public class ShowingServiceImpl implements ShowingService {

    private final ShowingRepository showingRepository;
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;

    public ShowingServiceImpl(ShowingRepository showingRepository,
                              MovieRepository movieRepository, TheaterRepository theaterRepository){
        this.showingRepository = showingRepository;
        this.movieRepository = movieRepository;
        this.theaterRepository = theaterRepository;
    }

    @Override
    public ShowingResponseDTO createShowing(CreateShowingRequestDTO req) {

        //Find film
        Movie movie = movieRepository.findById(req.movieId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Movie was not found, are you sure you searched correctly?"));

        //Find sal
        Theater theater = theaterRepository.findById(req.theaterId())
                .orElseThrow(() ->  new IllegalArgumentException("That theater does not exist..."));

        //Opret showing
        Showing showing = new Showing();
        showing.setMovie(movie);
        showing.setTheater(theater);
        showing.setStartTime(req.startTime());

        //Gem i databasen
        showing = showingRepository.save(showing);

        // Mapper entity data til ShowingResponse DTO
        // DTO'en sendes tilbage til klienten via controller
        return new ShowingResponseDTO(
                showing.getId(),
                movie.getTitle(),
                showing.getStartTime(),
                theater.getType().getDisplayName()
        );
    }

    @Override
    public List<ShowingResponseDTO> getUpcomingShowings() {
        return List.of();
    }
}
