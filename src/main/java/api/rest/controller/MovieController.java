package api.rest.controller;

import api.rest.domain.Movie;
import api.rest.requests.MoviePostRequestBody;
import api.rest.requests.MoviePutRequestBody;
import api.rest.service.MovieService;
import api.rest.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("movies")
@Log4j2
@AllArgsConstructor
public class MovieController {

    private final DateUtil dateUtil;
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<Page<Movie>> list(Pageable pageable){
        log.info(dateUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(movieService.listAll(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Movie> findById(@PathVariable long id){
        return ResponseEntity.ok(movieService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Movie>> findByName(@RequestParam String name){
        return ResponseEntity.ok(movieService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Movie> save( @RequestBody @Valid MoviePostRequestBody moviePostRequestBody){
        return new ResponseEntity<>(movieService.save(moviePostRequestBody),HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<Void> replace(@RequestBody @Valid MoviePutRequestBody moviePutRequestBody){
        movieService.replace(moviePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
