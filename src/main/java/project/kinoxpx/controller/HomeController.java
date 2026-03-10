package project.kinoxpx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/KinoXP")
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "KinoXP api is running";
    }

    // TODO:
}
