package project.kinoxpx.service;

import project.kinoxpx.dto.CreateShowingRequestDTO;
import project.kinoxpx.dto.CreateTheaterRequestDTO;
import project.kinoxpx.dto.ShowingResponseDTO;
import project.kinoxpx.dto.TheaterResponseDTO;

import java.util.List;

public interface ShowingService {

    ShowingResponseDTO createShowing(CreateShowingRequestDTO req);
    List<ShowingResponseDTO> getUpcomingShowings();
    ShowingResponseDTO updateShowing(Long id, CreateShowingRequestDTO req);
    void deleteShowing(Long id);

}
