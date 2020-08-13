package petcc.course.spring.sistemanotas.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import petcc.course.spring.sistemanotas.model.Aluno;
import petcc.course.spring.sistemanotas.service.AlunoService;
import petcc.course.spring.sistemanotas.util.AlunoCreator;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DisplayName("Testes da classe AlunoController")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AlunoControllerTest {

    @InjectMocks
    private AlunoController alunoControllerSUT;

    @Mock
    private AlunoService alunoServiceMock;

    @Test
    public void listAll_ReturnListOfAluno_WhenSuccessful() {

        /// CONFIGURAÇÃO
        Aluno aluno = AlunoCreator.creatingValidAluno();
        when(alunoServiceMock.findAll())
                .thenReturn(List.of(aluno));

        /// EXECUÇÃO
        List<Aluno> resultAlunoList = alunoControllerSUT.findAll().getBody();

        /// VERIFICAÇÃO
        assertAll("validations",
                () -> assertThat(resultAlunoList).isNotEmpty(),
                () -> assertThat(resultAlunoList).contains(aluno)
        );
    }

    @Test
    public void listAll_ReturnEmptyList_WhenThereIsNotAluno() {

        /// CONFIGURAÇÃO

        /// EXECUÇÃO
        List<Aluno> resultAlunoList = alunoControllerSUT.findAll().getBody();

        /// VERIFICAÇÃO
        assertThat(resultAlunoList).isEmpty();
    }

    @Test
    public void findByName_ReturnListOfAluno_WhenSuccessful() {

        /// CONFIGURAÇÃO
        Aluno aluno = AlunoCreator.creatingValidAluno();
        String expectedNome = aluno.getNome();
        when(alunoServiceMock.findByName(any()))
                .thenReturn(List.of(aluno));

        /// EXECUÇÃO
        List<Aluno> resultAlunoList = alunoControllerSUT.findByNome("ed").getBody();

        List<String> resultNomeOfAlunoList = resultAlunoList.stream()
                .map(Aluno::getNome).collect(Collectors.toList());

        /// VERIFICAÇÃO
        assertAll("validations",
                () -> assertThat(resultAlunoList).isNotNull(),
                () -> assertThat(resultAlunoList).isNotEmpty(),
                () -> assertThat(resultNomeOfAlunoList).contains(expectedNome)
        );
    }

    @Test
    public void findById_ReturnAluno_WhenSuccessful() {

        /// CONFIGURAÇÃO

        Aluno expectedAluno = AlunoCreator.creatingValidAluno();
        Integer expectedIdAluno = expectedAluno.getId();

        when(alunoServiceMock.findById(anyInt()))
                .thenReturn(expectedAluno);

        /// EXECUÇÃO
        Aluno resultAluno = alunoControllerSUT.findById(1).getBody();

        /// VERIFICAÇÃO

        assertAll("validations",
                () -> assertThat(resultAluno).isNotNull(),
                () -> assertThat(resultAluno.getNome()).isNotNull(),
                () -> assertThat(resultAluno.getNome()).isNotEmpty(),
                () -> assertThat(resultAluno.getId()).isEqualTo(expectedIdAluno)
        );
    }

    @Test
    public void save_CreateAluno_WhenSuccessful() {

        /// CONFIGURAÇÃO
        Aluno aluno = AlunoCreator.creatingAlunoToBeSave();
        String expectedNome = aluno.getNome();
        when(alunoServiceMock.save(any()))
                .thenReturn(aluno);

        /// EXECUÇÃO
        Aluno resultAluno = alunoControllerSUT.save(aluno).getBody();

        /// VERIFICAÇÃO
        assertAll("validations",
                () -> assertThat(resultAluno).isNotNull(),
                () -> assertThat(resultAluno.getNome()).isNotNull(),
                () -> assertThat(resultAluno.getNome()).isNotEmpty(),
                () -> assertThat(resultAluno.getNome()).isEqualTo(expectedNome)
        );
    }

    @Test
    public void delete_RemoveAluno_WhenSucessful() {

        /// CONFIGURAÇÃO
        doNothing().when(alunoServiceMock).delete(anyInt());

        /// EXECUÇÃO
        ResponseEntity<Void> resultResponseEntity = alunoControllerSUT.delete(1);

        /// VERIFICAÇÃO
        assertAll("validations",
                () -> assertThat(resultResponseEntity).isNotNull(),
                () -> assertThat(resultResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT)
        );
    }

    @Test
    public void update_SaveUpdatedAluno_WhenSucessful() {

        /// CONFIGURAÇÃO
        Aluno aluno = AlunoCreator.creatingValidUpdatedAluno();

        /// EXECUÇÃO
        ResponseEntity<Void> resultResponseEntity = alunoControllerSUT.update(aluno);

        /// VERIFICAÇÃO
        assertAll("validations",
                () -> assertThat(resultResponseEntity).isNotNull(),
                () -> assertThat(resultResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT)
        );
    }


}