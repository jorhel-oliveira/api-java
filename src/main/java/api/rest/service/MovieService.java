package api.rest.service;

import api.rest.domain.Movie;
import api.rest.repository.MovieRepository;
import api.rest.requests.MoviePostRequestBody;
import api.rest.requests.MoviePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> listAll(){
        return movieRepository.findAll();
    }

    public Movie findByIdOrThrowBadRequestException(long id){
        return movieRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Movie id not found"));
    }

    public Movie save(MoviePostRequestBody moviePostRequestBody) {
        return movieRepository.save(Movie.builder().name(moviePostRequestBody.getName()).build());
    }

    public void delete(long id) {
        movieRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(MoviePutRequestBody moviePutRequestBody) {
        findByIdOrThrowBadRequestException(moviePutRequestBody.getId());
        Movie movie = Movie.builder()
                .id(moviePutRequestBody.getId())
                .name(moviePutRequestBody.getName())
                .build();
        movieRepository.save(movie);
    }
}
