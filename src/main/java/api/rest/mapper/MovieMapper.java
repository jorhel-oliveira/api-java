package api.rest.mapper;

import api.rest.domain.Movie;
import api.rest.requests.MoviePostRequestBody;
import api.rest.requests.MoviePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {

    public static final MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    public abstract Movie toMovie(MoviePostRequestBody moviePostRequestBody);

    public abstract Movie toMovie(MoviePutRequestBody moviePostRequestBody);
}
