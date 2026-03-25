package project.kinoxpx.seat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ReservedSeatRepository extends JpaRepository<ReservedSeats, Long> {
    List<ReservedSeats> findByShowingId(Long showingId);
}
