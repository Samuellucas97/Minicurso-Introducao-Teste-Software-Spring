package petcc.course.spring.sistemanotas.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
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
    private Double nota;
    @NotNull(message = "O semestre da nota não pode ser nulo")
    private String semestre;
}