package petcc.course.spring.sistemanotas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Notas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Aluno aluno;
    @ManyToOne
    private Disciplina disciplina;
    @NotNull(message = "A nota não pode ser nula")
    @Min(value = 0, message = "A nota não pode ser negativa")
    private Double nota;
    @NotNull(message = "O semestre da nota não pode ser nulo")
    private String semestre;
}