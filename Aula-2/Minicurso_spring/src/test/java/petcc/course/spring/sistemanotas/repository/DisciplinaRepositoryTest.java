package petcc.course.spring.sistemanotas.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import petcc.course.spring.sistemanotas.model.Aluno;
import petcc.course.spring.sistemanotas.model.Disciplina;
import petcc.course.spring.sistemanotas.model.Professor;
import petcc.course.spring.sistemanotas.util.DisciplinaCreator;
import petcc.course.spring.sistemanotas.util.ProfessorCreator;

import javax.validation.ConstraintViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@DataJpaTest
@Slf4j
@DisplayName("Testes da classe DisciplinaRepository")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DisciplinaRepositoryTest {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public void sa (){
    }

//    @Test
//    @DisplayName("Save persiste uma nova disciplina no banco de dados quando bem sucedido")
//    public void save_PersistDisciplina_WhenSuccessful() {
//
//        /// CONFIGURAÇÃO
//        professorRepository.save(ProfessorCreator.creatingValidProfessor());
//        Disciplina expectedDisciplina = disciplinaRepository.save(DisciplinaCreator.creatingValidDisciplina());
//        String expectedNomeOfDisciplina = expectedDisciplina.getNome();
//        Professor expectedProfessorOfDisciplina = expectedDisciplina.getProfessor();
//
//        /// EXECUÇÃO
//        Disciplina resultDisciplina = disciplinaRepository.save(expectedDisciplina);
//
//        /// VERIFICAÇÃO
//        assertAll("validations",
//                () -> assertThat(resultDisciplina).isNotNull(),
//                () -> assertThat(resultDisciplina.getNome()).isNotNull(),
//                () -> assertThat(resultDisciplina.getNome()).isNotEmpty(),
//                () -> assertThat(resultDisciplina.getNome()).isEqualTo(expectedNomeOfDisciplina),
//                () -> assertThat(resultDisciplina.getProfessor()).isEqualTo(expectedProfessorOfDisciplina)
//        );
//
//
//        disciplinaRepository.deleteAll();
//        professorRepository.deleteAll();
//    }


    @Test
    @DisplayName("Save lança uma exceção do tipo ConstraintViolationException " +
            "quando o nome do aluno está vazio")
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

        disciplinaRepository.deleteAll();
        professorRepository.deleteAll();
    }


}