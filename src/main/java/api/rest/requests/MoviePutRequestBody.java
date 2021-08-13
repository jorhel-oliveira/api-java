package api.rest.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MoviePutRequestBody {

    private Long id;

    @NotEmpty(message = "name cannot be empty")
    private String name;
}
