package api.rest.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MoviePostRequestBody {

    @NotEmpty(message = "name cannot be empty")
    private String name;
}
