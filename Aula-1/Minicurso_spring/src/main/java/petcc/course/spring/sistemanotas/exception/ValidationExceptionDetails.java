package petcc.course.spring.sistemanotas.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {
    private String campos;
    private String messagemCampos;
}
