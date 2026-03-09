package project.kinoxpx.repository;

import project.kinoxpx.model.Reservation;
import project.kinoxpx.model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowingRepository extends JpaRepository<Showing, Long> {
    List<Showing> findByStartTimeAfterOrderByStartTimeAsc(LocalDateTime now);
}
