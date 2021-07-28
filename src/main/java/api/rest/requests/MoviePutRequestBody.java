package api.rest.requests;

import lombok.Data;

@Data
public class MoviePutRequestBody {
    private Long id;
    private String name;
}
