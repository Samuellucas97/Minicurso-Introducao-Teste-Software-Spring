package petcc.course.spring.sistemanotas.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import petcc.course.spring.sistemanotas.exception.ResourceNotFoundDetails;
import petcc.course.spring.sistemanotas.exception.ResourceNotFoundException;
import petcc.course.spring.sistemanotas.exception.ValidationExceptionDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDetails> handleResourceNotFoundException(
            ResourceNotFoundException exception){
        return new ResponseEntity<>(
                ResourceNotFoundDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .titulo("Recurso não encontrado")
                        .detalhes(exception.getMessage())
                        .mensagem(exception.getClass().getName())
                        .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception){

        List<FieldError> fieldErrorList = exception.getBindingResult().getFieldErrors();

        String campos = fieldErrorList.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String mensagem= fieldErrorList.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .titulo("Argumento(s) de requisição errado(s)")
                        .detalhes(exception.getMessage())
                        .mensagem(exception.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }
}
