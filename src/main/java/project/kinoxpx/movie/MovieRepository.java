package project.kinoxpx.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitleIgnoreCase(String title);
}
