package project.kinoxpx.repository;

import org.springframework.stereotype.Repository;
import project.kinoxpx.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
}
