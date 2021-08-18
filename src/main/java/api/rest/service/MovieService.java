package api.rest.service;

import api.rest.domain.Movie;
import api.rest.exception.BadRequestException;
import api.rest.mapper.MovieMapper;
import api.rest.repository.MovieRepository;
import api.rest.requests.MoviePostRequestBody;
import api.rest.requests.MoviePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Page<Movie> listAll(Pageable pageable){
        return movieRepository.findAll(pageable);
    }

    public List<Movie> findByName(String name) {
        return movieRepository.findByName(name);
    }

    public Movie findByIdOrThrowBadRequestException(long id){
        return movieRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("Movie id not found"));
    }

    @Transactional(rollbackFor = Exception.class)
    public Movie save(MoviePostRequestBody moviePostRequestBody) {
        return movieRepository.save(MovieMapper.INSTANCE.toMovie(moviePostRequestBody));
    }

    public void delete(long id) {
        movieRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public void replace(MoviePutRequestBody moviePutRequestBody) {
        Movie savedMovie = findByIdOrThrowBadRequestException(moviePutRequestBody.getId());
        Movie movie = MovieMapper.INSTANCE.toMovie(moviePutRequestBody);
        movie.setId(savedMovie.getId());
        movieRepository.save(movie);
    }
}
