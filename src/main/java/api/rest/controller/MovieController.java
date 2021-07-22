package api.rest.controller;

import api.rest.domain.Movie;
import api.rest.service.MovieService;
import api.rest.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Movie>> list(){
        log.info(dateUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(movieService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Movie> findById(@PathVariable long id){
        return ResponseEntity.ok(movieService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Movie> save(@RequestBody Movie movie){
        return new ResponseEntity<>(movieService.save(movie),HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<Void> replace(@RequestBody Movie movie){
        movieService.replace(movie);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
