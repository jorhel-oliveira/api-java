package api.rest.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidateExceptionDetails extends ExceptionDetails{
    private final String fields;
    private final String fieldsMessage;
}
