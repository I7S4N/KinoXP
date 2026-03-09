package project.kinoxpx.service;

import project.kinoxpx.dto.CreateShowingRequestDTO;
import project.kinoxpx.dto.ShowingResponseDTO;

import java.util.List;

public interface ShowingService {

    ShowingResponseDTO createShowing(CreateShowingRequestDTO req);
    List<ShowingResponseDTO> getUpcomingShowings();

}
