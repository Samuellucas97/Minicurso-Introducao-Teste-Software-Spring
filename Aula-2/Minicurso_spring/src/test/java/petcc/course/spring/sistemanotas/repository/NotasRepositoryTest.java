package petcc.course.spring.sistemanotas.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import petcc.course.spring.sistemanotas.model.Disciplina;
import petcc.course.spring.sistemanotas.model.Professor;
import petcc.course.spring.sistemanotas.util.DisciplinaCreator;
import petcc.course.spring.sistemanotas.util.ProfessorCreator;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Slf4j
@DisplayName("Testes da classe NotasRepository")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NotasRepositoryTest {

    @Autowired
    private NotasRepository notasRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Test
    @DisplayName("Save persiste uma nova nota no banco de dados quando bem sucedido")
    public void save_PersistNotas_WhenSuccessful() {

        /// Configuração do ambiente necessário para o teste

        Disciplina disciplina = DisciplinaCreator.creatingValidDisciplina();
        String expectedNome = disciplina.getNome();
        Professor expectedProfessor = disciplina.getProfessor();
        professorRepository.save(ProfessorCreator.creatingValidProfessor());

        /// -> Execução do método a ser testado

        Disciplina resultDisciplina = disciplinaRepository.save(disciplina);

        log.info("Result disciplina id: {}", resultDisciplina);

        /// -> Verificação do resultado quanto ao fato de se ocorre o esperado

        assertAll("validations",
                () -> assertThat(resultDisciplina).isNotNull(),
                () -> assertThat(resultDisciplina.getNome()).isNotNull(),
                () -> assertThat(resultDisciplina.getNome()).isNotEmpty(),
                () -> assertThat(resultDisciplina.getNome()).isEqualTo(expectedNome),
                () -> assertThat(resultDisciplina.getProfessor()).isEqualTo(expectedProfessor)
        );
    }


    @Test
    @DisplayName("Save lança uma exceção do tipo ConstraintViolationException " +
            "quando o 0 do aluno está vazio")
    public void save_ThrowConstraintViolationException_WhenNomeIsEmpty() {

        /// CONFIGURAÇÃO
        Disciplina disciplina = Disciplina.builder()
                .nome("")
                .professor(ProfessorCreator.creatingValidProfessor())
                .build();

        /// EXECUÇÃO e VERIFICAÇÃO
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(()-> disciplinaRepository.save(disciplina))
                .withMessageContaining("O nome da disciplina não pode ser vazio");
    }

    @Test
    public void findByProfessor_ReturnProfessor_WhenSuccessful() {

        /// CONFIGURAÇÃO
        professorRepository.save(ProfessorCreator.creatingValidProfessor());
        Disciplina expectedDisciplina = disciplinaRepository.save(DisciplinaCreator.creatingValidDisciplina());
        String expectedNomeOfDisciplina = expectedDisciplina.getNome();
        Professor expectedProfessorOfDisciplina = expectedDisciplina.getProfessor();

        /// EXECUÇÃO
        Disciplina resultDisciplina = disciplinaRepository.findByProfessor(expectedProfessorOfDisciplina).get();

        /// VERIFICAÇÃO

        assertAll("validations",
                () -> assertThat(resultDisciplina).isNotNull(),
                () -> assertThat(resultDisciplina.getNome()).isNotNull(),
                () -> assertThat(resultDisciplina.getNome()).isNotEmpty(),
                () -> assertThat(resultDisciplina.getNome()).isEqualTo(expectedNomeOfDisciplina),
                () -> assertThat(resultDisciplina.getProfessor()).isEqualTo(expectedProfessorOfDisciplina)
        );
    }
}