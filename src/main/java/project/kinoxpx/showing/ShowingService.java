package project.kinoxpx.showing;

import java.util.List;

public interface ShowingService {

    List<ShowingResponseDTO> getTodayShowings();
    ShowingResponseDTO createShowing(CreateShowingRequestDTO req);
    List<ShowingResponseDTO> getUpcomingShowings();
    List<ShowingResponseDTO> getAllShowings();
    ShowingResponseDTO updateShowing(Long id, CreateShowingRequestDTO req);
    void deleteShowing(Long id);

}
