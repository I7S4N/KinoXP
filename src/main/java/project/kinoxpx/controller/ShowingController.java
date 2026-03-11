package project.kinoxpx.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.kinoxpx.dto.CreateShowingRequestDTO;
import project.kinoxpx.dto.ShowingResponseDTO;
import project.kinoxpx.service.ShowingService;

@RestController
@RequestMapping("/showings")
public class ShowingController {

    public ShowingController(ShowingService showingService) {
        this.showingService = showingService;
    }

    private final ShowingService showingService;


    @PostMapping
    public ShowingResponseDTO createShowing(@RequestBody CreateShowingRequestDTO request){
        return showingService.createShowing(request);
    }
}
