package petcc.course.spring.sistemanotas.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import petcc.course.spring.sistemanotas.exception.ResourceNotFoundDetails;
import petcc.course.spring.sistemanotas.exception.ResourceNotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDetails> handleResourceNotFoundException(ResourceNotFoundException exception){
        return new ResponseEntity<>(
                ResourceNotFoundDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .titulo("Recurso não encontrado")
                        .detalhes(exception.getMessage())
                        .mensagem(exception.getClass().getName())
                        .build(), HttpStatus.NOT_FOUND);
    }
}
