package petcc.course.spring.sistemanotas.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import petcc.course.spring.sistemanotas.model.Aluno;

import javax.validation.ConstraintViolationException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Classe de teste de AlunoRepository")
class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    @DisplayName("Save persiste um novo aluno no banco de dados quando bem sucedido")
    void save_PersistAluno_WhenSuccessful() {

        /// Configuração do ambiente necessário para o teste

        String expectedNome = "Samuel";

        Aluno aluno = Aluno.builder()
                        .nome(expectedNome)
                        .cpf("51050218000")
                        .curso("Tecnologia da Informação")
                        .email("samuel_novo@gmail.com")
                        .build();


        /// Execução do método a ser testado

        final Aluno resultAluno = alunoRepository.save(aluno);

        /// Verificação do resultado quanto ao fato de que ocorre o que se é esperado


        assertAll(
                () -> assertThat(resultAluno).isNotNull(),
                () -> assertThat(resultAluno.getNome()).isNotNull(),
                () -> assertThat(resultAluno.getNome()).isNotEmpty(),
                () -> assertThat(resultAluno.getNome()).isEqualTo(expectedNome)
        );

        /// Desconfigurando o ambiente necessário para o teste
    }

    @Test
    @DisplayName("Save lança uma exceção do tipo ConstraintViolationException " +
            "quando o nome do aluno está vazio")
    void save_ThrowConstraintViolationException_WhenNomeIsEmpty() {

        /// Configuração do ambiente necessário para o teste

        String expectedNome = "";

        Aluno aluno = Aluno.builder()
                .nome(expectedNome)
                .cpf("51050218000")
                .curso("Tecnologia da Informação")
                .email("samuel_novo@gmail.com")
                .build();


        /// Execução do método a ser testado e Verificação do resultado quanto ao
        // fato de que ocorre o que se é esperado

        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> alunoRepository.save(aluno))
                .withMessageContaining("O nome do aluno não pode ser vazio");

        /// Desconfigurando o ambiente necessário para o teste

    }

    @Test
    @DisplayName("FindByNome retorna lista de alunos quando um aluno com o correspondente " +
            "nome é encontrado")
    void findByNome_ReturnAlunos_WhenSuccesful() {

        // CONFIGURAÇÃO

        Aluno aluno = Aluno.builder()
                .nome("Samuel")
                .cpf("51050218000")
                .curso("Tecnologia da Informação")
                .email("samuel_novo@gmail.com")
                .build();

        final Aluno expectedAluno = alunoRepository.save(aluno);
        final String nameSavedAluno = expectedAluno.getNome();

        // EXECUÇÃO

        final List<Aluno> resultAlunoList = alunoRepository.findByNome(nameSavedAluno);

        // VERIFICAÇÃO

        assertAll(
            () -> assertThat(resultAlunoList).isNotEmpty(),
            () -> assertThat(resultAlunoList).contains(expectedAluno)
        );
    }

    @Test
    @DisplayName("FindByNome retorna lista de alunos vazio quando aluno não é encontrado")
    void findByNome_ReturnEmptyList_WhenAlunoNotFound() {

        // CONFIGURAÇÃO

        String name = "Junior";

        // EXECUÇÃO

        final List<Aluno> resultAlunoList = alunoRepository.findByNome(name);

        // VERIFICAÇÃO

        assertThat(resultAlunoList).isEmpty();

    }


}