package api.rest.repository;

import api.rest.domain.Movie;

import java.util.List;

public interface MovieRepository {
    List <Movie> listAll();
}
