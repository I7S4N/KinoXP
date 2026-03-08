package project.kinoxpx.Service;

import project.kinoxpx.Model.Showing;
import project.kinoxpx.Repository.ShowingRepository;

import java.util.List;

public class ShowingService {

    private final ShowingRepository showingRepository;

    public ShowingService(ShowingRepository showingRepository) {
        this.showingRepository = showingRepository;
    }

    public List<Showing> getAllShowings() {
        return showingRepository.findAll();
    }

    public Showing createShowing(Showing showing) {
        return showingRepository.save(showing);
    }
}

