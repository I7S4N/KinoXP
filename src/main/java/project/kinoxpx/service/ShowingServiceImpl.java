package project.kinoxpx.service;

import org.springframework.stereotype.Service;
import project.kinoxpx.dto.*;
import project.kinoxpx.exception.InvalidRequestException;
import project.kinoxpx.exception.ResourceNotFoundException;
import project.kinoxpx.model.Movie;
import project.kinoxpx.model.Showing;
import project.kinoxpx.model.Theater;
import project.kinoxpx.repository.MovieRepository;
import project.kinoxpx.repository.ShowingRepository;
import project.kinoxpx.repository.TheaterRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
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

    // Mapper Movie entity til ShowingResponseDTO
    private ShowingResponseDTO mapToDTO(Showing showing) {
        return new ShowingResponseDTO(
                showing.getId(),
                showing.getMovie().getTitle(),
                showing.getStartTime(),
                showing.getTheater().getType().getDisplayName()
        );
    }

    @Override
    public ShowingResponseDTO createShowing(CreateShowingRequestDTO req) {

        // Finder filmen i databasen via movieId fra request DTO
        // Hvis filmen ikke findes, kaster vi en fejl
        Movie movie = movieRepository.findById(req.movieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie with ID " + req.movieId() + " was not found"));


        // Finder salen i databasen via theaterId fra request DTO
        // Hvis den ikke findes, kaster vi en fejl
        Theater theater = theaterRepository.findById(req.theaterId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Theater with ID " + req.theaterId() + " does not exist"));


        // Opretter en ny Showing entity
        // En showing består af en film, en sal og et starttidspunkt
        Showing showing = new Showing();
        showing.setMovie(movie);
        showing.setTheater(theater);
        showing.setStartTime(req.startTime());

        //Gemmer showing i databasen
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

        // Finder alle showings der starter efter nuværende tidspunkt
        // Resultatet sorteres automatisk efter startTime

        return showingRepository.findByStartTimeAfterOrderByStartTimeAsc(LocalDateTime.now()).stream()


                .map(showing -> new ShowingResponseDTO(
                        showing.getId(),
                        showing.getMovie().getTitle(),
                        showing.getStartTime(),
                        showing.getTheater().getType().getDisplayName()
                ))
                .toList();
    }

    @Override
    public ShowingResponseDTO updateShowing(Long id, CreateShowingRequestDTO req) {
        Showing showing = showingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Showing with ID " + id + " was not found"));

        if (req.movieId() <= 0) {
            throw new InvalidRequestException("Could not find showing");
        }

        LocalDateTime start = req.startTime();

//      må ikke være i fortiden
        if (start.isBefore(LocalDateTime.now())) {
            throw new InvalidRequestException("Showing cannot be in the past");
        }

//      må kun starte mellem 10 og 22
        LocalTime time = start.toLocalTime();

        if (time.isBefore(LocalTime.of(10,0)) || time.isAfter(LocalTime.of(22,0))) {
            throw new InvalidRequestException("Showings must start between 10:00 and 22:00");
        }

        Movie movie = movieRepository.findById(req.movieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        Theater theater = theaterRepository.findById(req.theaterId())
                .orElseThrow(() -> new ResourceNotFoundException("Theater not found"));

        showing.setMovie(movie);
        showing.setStartTime(req.startTime());
        showing.setTheater(theater);

        return mapToDTO(showingRepository.save(showing));
    }

    @Override
    public void deleteShowing(Long id) {
        if (!showingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Showing with ID " + id + " was not found");
        }
        showingRepository.deleteById(id);
    }
}
