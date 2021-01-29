package petcc.course.spring.sistemanotas.exception;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {
    private String titulo;
    private Integer status;
    private String detalhes;
    private LocalDateTime timestamp;
    private String mensagem;
}
