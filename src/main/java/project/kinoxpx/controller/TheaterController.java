package project.kinoxpx.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.kinoxpx.dto.CreateTheaterRequestDTO;
import project.kinoxpx.dto.TheaterResponseDTO;
import project.kinoxpx.model.Theater;
import project.kinoxpx.service.TheaterService;

import java.util.List;

@RestController
@RequestMapping("api/theaters")
public class TheaterController {

    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @PostMapping
    public ResponseEntity<TheaterResponseDTO> createTheater(@RequestBody CreateTheaterRequestDTO req) {
        TheaterResponseDTO response = theaterService.createTheater(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TheaterResponseDTO>> getAllTheaters() {
        List<TheaterResponseDTO> theaters = theaterService.getAllTheaters();
        return ResponseEntity.ok(theaters);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TheaterResponseDTO> updateTheater(@PathVariable Long id, @RequestBody CreateTheaterRequestDTO req) {
        return ResponseEntity.ok(theaterService.updateTheater(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long id) {
        theaterService.deleteTheater(id);
        return ResponseEntity.noContent().build();
    }
}

