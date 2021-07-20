package api.rest.service;

import api.rest.domain.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MovieService {

    private List<Movie> movies = List.of(new Movie(1L,"O Conde de Monte Cristo"), new Movie(2L,"Stalone Cobra"));;
    public List<Movie> listAll(){
        return movies;
    }

    public Movie findById(long id){
        return movies.stream().
                filter(movie -> movie.getId()
                        .equals(id))
                        .findFirst()
                        .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Movie id not found"));
    }

    public Movie save(Movie movie) {
        return null;
    }
}
