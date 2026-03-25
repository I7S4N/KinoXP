package project.kinoxpx.theater;

import org.springframework.stereotype.Service;
import project.kinoxpx.exceptions.InvalidRequestException;
import project.kinoxpx.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
class TheaterServiceImpl implements TheaterService {

    private final TheaterRepository theaterRepository;

    public TheaterServiceImpl(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    // Mapper Theater entity til TheaterResponseDTO
    private TheaterResponseDTO mapToDTO(Theater theater) {
        return new TheaterResponseDTO(
                theater.getId(),
                theater.getNumberOfRows(),
                theater.getSeatsPerRow(),
                theater.getType().getDisplayName()
        );
    }

    @Override
    public TheaterResponseDTO createTheater(CreateTheaterRequestDTO req) {

        // Validering
        if (req.numberOfRows() <= 0) {
            throw new InvalidRequestException("Number of rows must be greater than 0");
        }

        if (req.seatsPerRow() <= 0) {
            throw new InvalidRequestException("Seats per row must be greater than 0");
        }

        if (req.type() == null) {
            throw new InvalidRequestException("Theater type cannot be null");
        }

        // Mapper DTO til Theater entity
        Theater theater = new Theater();
        theater.setNumberOfRows(req.numberOfRows());
        theater.setSeatsPerRow(req.seatsPerRow());
        theater.setType(req.type());

        // Gemmer i databasen
        theater = theaterRepository.save(theater);

        // Returnerer DTO til klienten
        return mapToDTO(theater);
    }

    @Override
    public List<TheaterResponseDTO> getAllTheaters() {
        return theaterRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public TheaterResponseDTO updateTheater(Long id, CreateTheaterRequestDTO req) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Theater with ID " + id + " was not found"));

        if (req.numberOfRows() <= 0) {
            throw new InvalidRequestException("Number of rows must be greater than 0");
        }
        if (req.seatsPerRow() <= 0) {
            throw new InvalidRequestException("Seats per row must be greater than 0");
        }
        if (req.type() == null) {
            throw new InvalidRequestException("Theater type cannot be null");
        }

        theater.setNumberOfRows(req.numberOfRows());
        theater.setSeatsPerRow(req.seatsPerRow());
        theater.setType(req.type());

        return mapToDTO(theaterRepository.save(theater));
    }

    @Override
    public void deleteTheater(Long id) {
        if (!theaterRepository.existsById(id)) {
            throw new ResourceNotFoundException("Theater with ID " + id + " was not found");
        }
        theaterRepository.deleteById(id);
    }
}
