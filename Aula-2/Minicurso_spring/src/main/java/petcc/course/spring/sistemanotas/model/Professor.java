package petcc.course.spring.sistemanotas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "O nome do professor não pode ser nulo")
    @NotEmpty(message = "O nome do professor não pode ser vazio")
    private String nome;
    @CPF(message = "O CPF do professor é inválido")
    @Column(unique = true)
    private String cpf;
    @Email(message = "O email do professor é inválido")
    @Column(unique = true)
    private String email;
}