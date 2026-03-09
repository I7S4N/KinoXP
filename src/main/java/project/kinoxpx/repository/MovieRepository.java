package project.kinoxpx.repository;

import org.springframework.stereotype.Repository;
import project.kinoxpx.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
