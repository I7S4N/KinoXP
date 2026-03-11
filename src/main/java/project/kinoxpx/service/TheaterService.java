package project.kinoxpx.service;

import project.kinoxpx.dto.CreateTheaterRequestDTO;
import project.kinoxpx.dto.TheaterResponseDTO;

import java.util.List;

public interface TheaterService {
    TheaterResponseDTO createTheater(CreateTheaterRequestDTO req);
    List<TheaterResponseDTO> getAllTheaters();
    TheaterResponseDTO updateTheater(Long id, CreateTheaterRequestDTO req);
    void deleteTheater(Long id);
}