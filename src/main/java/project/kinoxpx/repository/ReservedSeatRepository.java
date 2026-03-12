package project.kinoxpx.repository;

import project.kinoxpx.model.ReservedSeats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservedSeatRepository extends JpaRepository<ReservedSeats, Long> {
    List<ReservedSeats> findByShowingId(Long showingId);
}
