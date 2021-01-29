package petcc.course.spring.sistemanotas.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import petcc.course.spring.sistemanotas.exception.ResourceNotFoundException;
import petcc.course.spring.sistemanotas.model.Aluno;
import petcc.course.spring.sistemanotas.service.AlunoService;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AlunoControllerTest {
    @InjectMocks
    private AlunoController alunoControllerSUT;
    @Mock
    private AlunoService alunoServiceMock;

    @Test
    void findAll_ReturnListOfAluno_WhenSuccessful() {
        final Aluno aluno = createValidAluno();
        when(alunoServiceMock.findAll())
                .thenReturn(List.of(aluno));

        final ResponseEntity<List<Aluno>> resultResponse = alunoControllerSUT.findAll();

        final List<Aluno> resultAlunoList = resultResponse.getBody();

        assertAll(
            () -> assertThat(resultResponse.getStatusCode()).isEqualTo(HttpStatus.OK),
            () -> assertThat(resultAlunoList).isNotEmpty(),
            () -> assertThat(resultAlunoList).contains(aluno),
            () -> assertThat(resultAlunoList).containsExactly(aluno)
        );
    }

    @Test
    void findAll_ReturnEmptyList_WhenThereIsNotAluno() {
        final ResponseEntity<List<Aluno>> resultResponse = alunoControllerSUT.findAll();
        final List<Aluno> resultAlunoList = resultResponse.getBody();

        assertAll(
            () -> assertThat(resultResponse.getStatusCode()).isEqualTo(HttpStatus.OK),
            () -> assertThat(resultAlunoList).isEmpty()
        );
    }

    @Test
    void findByName_ReturnAlunoList_WhenSuccessful() {
        final Aluno validAluno = createValidAluno();
        final String expectedNome = validAluno.getNome();
        when(alunoServiceMock.findByName(any()))
                .thenReturn(List.of(validAluno));

        final ResponseEntity<List<Aluno>> resultResponse = alunoControllerSUT.findByNome("ed");
        final List<Aluno> resultAlunoList = resultResponse.getBody();

        final List<String> namesOfAlunos = resultAlunoList.stream().map(Aluno::getNome)
                .collect(Collectors.toList());

        assertAll(
                () -> assertThat(resultResponse.getStatusCode()).isEqualTo(HttpStatus.OK),
                () -> assertThat(resultAlunoList).isNotNull(),
                () -> assertThat(resultAlunoList).isNotEmpty(),
                () -> assertThat(namesOfAlunos).containsExactly(expectedNome)
        );
    }

    @Test
    void findById_ReturnAluno_WhenSuccessful() {
        final Aluno validAluno = createValidAluno();
        final Integer expectedIdAluno = validAluno.getId();
        when(alunoServiceMock.findById(anyInt()))
                .thenReturn(validAluno);

        final ResponseEntity<Aluno> resultResponse = alunoControllerSUT.findById(1);
        final Aluno resultAluno = resultResponse.getBody();

        assertAll(
            () -> assertThat(resultResponse.getStatusCode()).isEqualTo(HttpStatus.OK),
            () -> assertThat(resultAluno).isNotNull(),
            () -> assertThat(resultAluno.getNome()).isNotNull(),
            () -> assertThat(resultAluno.getId()).isEqualTo(expectedIdAluno)
        );
    }

    @Test
    void findById_ThrowResourceNotFound_WhenThereIsNotAluno() {

        when(alunoServiceMock.findById(anyInt()))
                .thenThrow(new ResourceNotFoundException("Aluno não encontrado"));

        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> alunoControllerSUT.findById(1));
    }

    @Test
    void save_CreateAluno_WhenSuccessuful() {

        final Aluno aluno = Aluno.builder()
                .nome("Junior")
                .cpf("61824635036")
                .curso("Tecnologia da Informação")
                .email("abc@gmail.com")
                .build();

        final Aluno validAluno = createValidAluno();

        when(alunoServiceMock.save(aluno))
                .thenReturn(validAluno);

        final ResponseEntity<Aluno> resultResponse = alunoControllerSUT.save(aluno);
        final Aluno resultAluno = resultResponse.getBody();

        assertAll(
                () -> assertThat(resultResponse.getStatusCode()).isEqualTo(HttpStatus.OK),
                () -> assertThat(resultAluno).isNotNull(),
                () -> assertThat(resultAluno.getNome()).isNotNull(),
                () -> assertThat(resultAluno.getNome()).isEqualTo(aluno.getNome()),
                () -> assertThat(resultAluno.getEmail()).isNotNull(),
                () -> assertThat(resultAluno.getEmail()).isEqualTo(aluno.getEmail()),
                () -> assertThat(resultAluno.getCpf()).isNotNull(),
                () -> assertThat(resultAluno.getCpf()).isEqualTo(aluno.getCpf()),
                () -> assertThat(resultAluno.getCurso()).isNotNull(),
                () -> assertThat(resultAluno.getCurso()).isEqualTo(aluno.getCurso())
        );

    }

    @Test
    void delete_RemoveAluno_WhenSuccessful() {
        doNothing().when(alunoServiceMock).delete(anyInt());

        final ResponseEntity<Void> resultResponse = alunoControllerSUT.delete(1);

        assertAll(
                () -> assertThat(resultResponse).isNotNull(),
                () -> assertThat(resultResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT)
        );
    }

    @Test
    void delete_ThrowsResourceNotFoundException_WhenAlunoDoesNotExist() {

        doThrow(ResourceNotFoundException.class)
                .when(alunoServiceMock)
                .delete(anyInt());

        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> alunoControllerSUT.delete(1));

    }

    private Aluno createValidAluno() {
        return Aluno.builder()
                .id(1)
                .nome("Junior")
                .cpf("61824635036")
                .curso("Tecnologia da Informação")
                .email("abc@gmail.com")
                .build();
    }


}