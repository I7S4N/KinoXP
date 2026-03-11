package project.kinoxpx.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.kinoxpx.dto.CreateShowingRequestDTO;
import project.kinoxpx.dto.ShowingResponseDTO;
import project.kinoxpx.service.ShowingService;

import java.net.URI;
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
    public ResponseEntity<ShowingResponseDTO> createShowing(@Valid @RequestBody CreateShowingRequestDTO showingRequest) {
        ShowingResponseDTO newShowing = showingService.createShowing(showingRequest);
        return ResponseEntity.created(URI.create("/api/users/" + newShowing.id())).body(newShowing);
    }

    // TODO: update showing

    // TODO: delete showing

}
