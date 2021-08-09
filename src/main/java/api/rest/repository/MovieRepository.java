package api.rest.repository;

import api.rest.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MovieRepository extends JpaRepository<Movie,Long> {

    List<Movie> findByName(String name);
}
