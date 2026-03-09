package project.kinoxpx.repository;

import org.springframework.stereotype.Repository;
import project.kinoxpx.model.Reservation;
import project.kinoxpx.model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowingRepository extends JpaRepository<Reservation, Long> {
    List<Showing> findByStartTimeAfterOrderByStartTimeAsc(LocalDateTime now);
}
