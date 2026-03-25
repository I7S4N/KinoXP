package project.kinoxpx.theater;

import java.util.List;

public interface TheaterService {
    TheaterResponseDTO createTheater(CreateTheaterRequestDTO req);
    List<TheaterResponseDTO> getAllTheaters();
    TheaterResponseDTO updateTheater(Long id, CreateTheaterRequestDTO req);
    void deleteTheater(Long id);
}