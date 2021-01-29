package petcc.course.spring.sistemanotas.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import petcc.course.spring.sistemanotas.exception.ResourceNotFoundException;
import petcc.course.spring.sistemanotas.model.Aluno;
import petcc.course.spring.sistemanotas.repository.AlunoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DisplayName("Classe de teste de AlunoService")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoServiceSUT;
    @Mock
    private AlunoRepository alunoRepositoryMock;

    @Test
    void findAll_ReturnAlunoList_WhenSuccessful() {
     
        // CONFIGURAÇÃO
        final Aluno validAluno = creatingValidAluno();

        when(alunoRepositoryMock.findAll())
                .thenReturn(List.of(validAluno));

        // EXECUÇÃO
        final List<Aluno> resultAlunoList = alunoServiceSUT.findAll();

        // VERIFICAÇÃO
        assertAll(
                () -> assertThat(resultAlunoList).isNotEmpty(),
                () -> assertThat(resultAlunoList).contains(validAluno)
        );
    }

    @Test
    void findAll_ReturnEmptyList_WhenThereIsNotAluno() {

        // CONFIGURAÇÃO

        // EXECUÇÃO
        final List<Aluno> resultAlunoList = alunoServiceSUT.findAll();

        // VERIFICAÇÃO
        assertThat(resultAlunoList).isEmpty();
    }

    @Test
    void findByName_ReturnListOfAluno_WhenSuccessful() {

        // CONFIGURAÇÃO
        final Aluno validAluno = creatingValidAluno();

        final String expectedNameAluno = validAluno.getNome();

        when(alunoRepositoryMock.findByNome(any()))
                .thenReturn(List.of(validAluno));

        // EXECUÇÃO
        final List<Aluno> resultAlunoList = alunoServiceSUT.findByName("Junior");

        // VERIFICAÇÃO
        final List<String> resultNomeOfAlunoList = resultAlunoList.stream()
                .map(Aluno::getNome).collect(Collectors.toList());

        assertAll(
            () -> assertThat(resultAlunoList).isNotEmpty(),
            () -> assertThat(resultNomeOfAlunoList).contains(expectedNameAluno),
            () -> assertThat(resultNomeOfAlunoList).containsExactly(expectedNameAluno)
        );
    }

    @Test
    void findById_ReturnAluno_WhenSuccessful() {

        final Aluno validAluno = creatingValidAluno();
        final Integer expectedIdAluno = validAluno.getId();

        when(alunoRepositoryMock.findById(anyInt()))
                .thenReturn(Optional.of(validAluno));


        final Aluno resultAluno = alunoServiceSUT.findById(1);


        assertAll(
                () -> assertThat(resultAluno).isNotNull(),
                () -> assertThat(resultAluno.getId()).isNotNull(),
                () -> assertThat(resultAluno.getId()).isEqualTo(expectedIdAluno)
        );
    }

    @Test
    void findById_ThrowsResourceNotFoundException_WhenAlunoDoesNotExist() {

        when(alunoRepositoryMock.findById(anyInt()))
                .thenThrow(new ResourceNotFoundException("Aluno não encontrado"));

        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> alunoServiceSUT.findById(1));
    }

    @Test
    void save_CreateAluno_WhenSuccessful() {

        final Aluno expectedAluno = creatingValidAluno();
        final Integer expectedIdAluno = expectedAluno.getId();

        final Aluno alunoToBeSaved = Aluno.builder()
                .nome("Junior")
                .cpf("61824635036")
                .curso("Tecnologia da Informação")
                .email("abc@gmail.com")
                .build();

        when(alunoRepositoryMock.save(alunoToBeSaved))
                .thenReturn(expectedAluno);


        final Aluno resultAluno = alunoServiceSUT.save(alunoToBeSaved);


        assertAll(
                () -> assertThat(resultAluno).isNotNull(),
                () -> assertThat(resultAluno.getId()).isNotNull(),
                () -> assertThat(resultAluno.getId()).isEqualTo(expectedIdAluno)
        );
    }

    @Test
    void delete_RemoveAluno_WhenSuccessful() {
        final Aluno validAluno = creatingValidAluno();

        when(alunoRepositoryMock.findById(anyInt()))
                .thenReturn(Optional.of(validAluno));

        doNothing().when(alunoRepositoryMock).delete(any(Aluno.class));



        assertThatCode( () -> alunoServiceSUT.delete(1))
                .doesNotThrowAnyException();
    }

    @Test
    void delete_ThrowsResourceNotFoundException_WhenAlunoDoesNotExist() {

        when(alunoRepositoryMock.findById(anyInt()))
                .thenThrow(new ResourceNotFoundException("Aluno não encontrado"));

        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> alunoServiceSUT.delete(1));

    }

    private Aluno creatingValidAluno() {
        return Aluno.builder()
                .id(1)
                .nome("Junior")
                .cpf("61824635036")
                .curso("Tecnologia da Informação")
                .email("abc@gmail.com")
                .build();
    }
}