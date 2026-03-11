package project.kinoxpx.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.kinoxpx.dto.ShowingResponseDTO;
import project.kinoxpx.service.ShowingService;

import java.util.List;

@RestController
@RequestMapping("/api/showings")
public class ShowingController {
    private final ShowingService showingService;

    public ShowingController(ShowingService showingService) {
        this.showingService = showingService;
    }

    @GetMapping
    public ResponseEntity<List<ShowingResponseDTO>> getAllShowings () {
        return ResponseEntity.ok(showingService.getUpcomingShowings());
    }

    @PostMapping
    public ResponseEntity<ShowingResponseDTO> createShowing() {
        ShowingResponseDTO
    }
}
