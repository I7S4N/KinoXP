package repository;

import model.Reservation;
import model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowingRepository extends JpaRepository<Reservation, Long> {
    List<Showing> findByStartTimeAfterOrderByStartTimeAsc(LocalDateTime now);
}
