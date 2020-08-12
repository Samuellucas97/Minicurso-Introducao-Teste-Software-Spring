package petcc.course.spring.sistemanotas.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResourceNotFoundDetails {
    private String titulo;
    private Integer status;
    private String detalhes;
    private LocalDateTime timestamp;
    private String mensagem;
}