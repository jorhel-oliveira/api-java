package api.rest.handler;

import api.rest.exception.BadRequestException;
import api.rest.exception.BadRequestExceptionDetails;
import api.rest.exception.ValidateExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails>handlerBadRequestException(BadRequestException badRequestException){
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad BadRequestException")
                        .details(badRequestException.getMessage())
                        .build(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidateExceptionDetails>handlerMethodArgumentNotValidException
            (MethodArgumentNotValidException methodArgumentNotValidException){
        List<FieldError> fieldError = methodArgumentNotValidException.getBindingResult().getFieldErrors();

        String fields = fieldError.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fielsdmessage = fieldError.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidateExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Invalid fields")
                        .details(methodArgumentNotValidException.getMessage())
                        .fields(fields)
                        .fieldsMessage(fielsdmessage)
                        .build(),HttpStatus.BAD_REQUEST);
    }
}
