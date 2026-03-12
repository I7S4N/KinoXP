package project.kinoxpx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.kinoxpx.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
