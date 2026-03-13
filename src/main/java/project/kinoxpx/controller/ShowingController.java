package project.kinoxpx.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.kinoxpx.dto.CreateShowingRequestDTO;
import project.kinoxpx.dto.CreateTheaterRequestDTO;
import project.kinoxpx.dto.ShowingResponseDTO;
import project.kinoxpx.dto.TheaterResponseDTO;
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

    @GetMapping("/today")
    public ResponseEntity<List<ShowingResponseDTO>> getTodayShowings() {
        return ResponseEntity.ok(showingService.getTodayShowings());
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<ShowingResponseDTO>> getUpComingShowings() {
        return ResponseEntity.ok(showingService.getUpcomingShowings());
    }

    @PostMapping
    public ResponseEntity<ShowingResponseDTO> createShowing(@Valid @RequestBody CreateShowingRequestDTO showingRequest) {
        ShowingResponseDTO newShowing = showingService.createShowing(showingRequest);
        return ResponseEntity.created(URI.create("/api/users/" + newShowing.id())).body(newShowing);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowingResponseDTO> updateShowing(@PathVariable Long id, @RequestBody CreateShowingRequestDTO req) {
        return ResponseEntity.ok(showingService.updateShowing(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowing(@PathVariable Long id) {
        showingService.deleteShowing(id);
        return ResponseEntity.noContent().build();
    }

}
