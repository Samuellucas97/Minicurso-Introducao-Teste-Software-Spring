package petcc.course.spring.sistemanotas.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "O nome da disciplina não pode ser nulo")
    @NotEmpty(message = "O nome da disciplina não pode ser vazio")
    private String nome;
    @ManyToOne
    private Professor professor;
}