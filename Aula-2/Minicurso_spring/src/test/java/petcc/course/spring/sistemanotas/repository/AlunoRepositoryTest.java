package petcc.course.spring.sistemanotas.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import petcc.course.spring.sistemanotas.model.Aluno;
import petcc.course.spring.sistemanotas.util.AlunoCreator;

import javax.validation.ConstraintViolationException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Teste da classe AlunoRepository")
class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    @DisplayName("Save persiste um novo aluno no banco de dados quando bem sucedido")
    public void save_PersistAluno_WhenSuccessful() {

        /// Configuração do ambiente necessário para o teste

        Aluno aluno = AlunoCreator.creatingAlunoToBeSave();

        String expectedNome = aluno.getNome();

        /// -> Execução do método a ser testado

        Aluno resultAluno = alunoRepository.save(aluno);

        /// -> Verificação do resultado quanto ao fato de se ocorre o esperado

        assertAll("validations",
                () -> assertThat(resultAluno).isNotNull(),
                () -> assertThat(resultAluno.getNome()).isNotNull(),
                () -> assertThat(resultAluno.getNome()).isNotEmpty(),
                () -> assertThat(resultAluno.getNome()).isEqualTo(expectedNome)
        );
    }

    @Test
    @DisplayName("Save lança uma exceção do tipo ConstraintViolationException " +
            "quando o nome do aluno está vazio")
    public void save_ThrowConstraintViolationException_WhenNomeIsEmpty() {
        Aluno aluno = Aluno.builder()
                .nome("")
                .cpf("61824635036")
                .curso("Tecnologia da Informação")
                .email("abc@gmail.com")
                .build();

        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(()-> alunoRepository.save(aluno))
                .withMessageContaining("O nome do aluno não pode ser vazio");
    }

    @Test
    @DisplayName("FindByNome retorna lista de alunos quando um aluno com o correspondente " +
            "nome é encontrado")
    public void findByNome_ReturnAlunos_WhenSuccesful() {

        // CONFIGURAÇÃO

        Aluno aluno = AlunoCreator.creatingValidAluno();
        Aluno expectedAluno = alunoRepository.save(aluno);
        String nameSavedAluno = expectedAluno.getNome();

        // EXECUÇÃO

        List<Aluno> resultAlunoList = alunoRepository.findByNome(nameSavedAluno);

        // VERIFICAÇÃO

        assertAll("validations",
                () -> assertThat(resultAlunoList).isNotEmpty(),
                () -> assertThat(resultAlunoList).contains(expectedAluno)
        );
    }

    @Test
    @DisplayName("FindByNome retorna lista de alunos vazio quando aluno não é encontrado")
    public void findByNome_ReturnEmptyList_WhenAlunoNotFound() {

        // CONFIGURAÇÃO

        String name = "Junior";

        // EXECUÇÃO

        List<Aluno> resultAlunoList = alunoRepository.findByNome(name);

        // VERIFICAÇÃO

        assertThat(resultAlunoList).isEmpty();
    }




}