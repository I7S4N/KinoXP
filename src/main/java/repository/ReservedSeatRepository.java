package repository;

import model.ReservedSeats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservedSeatRepository extends JpaRepository<ReservedSeats, Long> {
    List<ReservedSeats>findByReservation_Showing_Id(Long showingId);
}
