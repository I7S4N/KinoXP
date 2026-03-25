package project.kinoxpx.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
