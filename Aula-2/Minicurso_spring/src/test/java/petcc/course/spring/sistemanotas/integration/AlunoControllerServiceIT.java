package petcc.course.spring.sistemanotas.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import petcc.course.spring.sistemanotas.model.Aluno;
import petcc.course.spring.sistemanotas.repository.AlunoRepository;
import petcc.course.spring.sistemanotas.util.AlunoCreator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DisplayName("Testes de integração de Aluno")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AlunoControllerServiceIT {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private Integer port;
    @MockBean
    private AlunoRepository alunoRepositoryMock;

    @Test
    public void listAll_ReturnListOfAlunos_WhenSuccessful() {
        Aluno expectedAluno = AlunoCreator.creatingValidAluno();
        when(alunoRepositoryMock.findAll())
                .thenReturn(List.of(expectedAluno));

        List<Aluno> resultAlunoList = testRestTemplate.exchange("/alunos", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Aluno>>() {}).getBody();

        assertAll("validations",
                () -> assertThat(resultAlunoList).isNotNull(),
                () -> assertThat(resultAlunoList).isNotEmpty(),
                () -> assertThat(resultAlunoList).contains(expectedAluno)
        );
    }

    @Test
    public void findById_ReturnAluno_WhenSuccessful() {
        Aluno aluno = AlunoCreator.creatingValidAluno();
        Integer expectedId = aluno.getId();
        when(alunoRepositoryMock.findById(anyInt()))
                .thenReturn(Optional.of(AlunoCreator.creatingValidAluno()));

        Aluno resultAluno = testRestTemplate.getForObject("/alunos/{id}", Aluno.class, 1);

        assertAll("validations",
                () -> assertThat(resultAluno).isNotNull(),
                () -> assertThat(resultAluno.getId()).isNotNull(),
                () -> assertThat(resultAluno.getId()).isEqualTo(expectedId)
        );
    }

    @Test
    public void findByName_ReturnListOfAluno_WhenSuccessful() {
        Aluno aluno = AlunoCreator.creatingValidAluno();
        String expectedNomeAluno = aluno.getNome();

        when(alunoRepositoryMock.findByNome(anyString()))
                .thenReturn(List.of(aluno));

        List<Aluno> resultAlunoList = testRestTemplate.exchange("/alunos/busca-nome/{nome}",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Aluno>>() {
                }, "fake").getBody();

        List<String> resultNomeOfAlunoList = resultAlunoList.stream()
                .map(Aluno::getNome).collect(Collectors.toList());

        assertAll("validations",
                () -> assertThat(resultAlunoList).isNotNull(),
                () -> assertThat(resultAlunoList).isNotEmpty(),
                () -> assertThat(resultNomeOfAlunoList).contains(expectedNomeAluno)
        );
    }

    @Test
    public void save_CreatesAluno_WhenSuccessful() {
        Integer expectedIdAluno = AlunoCreator.creatingValidAluno().getId();
        Aluno aluno = AlunoCreator.creatingAlunoToBeSaved();
        when(alunoRepositoryMock.save(AlunoCreator.creatingAlunoToBeSaved()))
                .thenReturn(AlunoCreator.creatingValidAluno());

        Aluno resultAluno = testRestTemplate.exchange("/alunos", HttpMethod.POST,
                new HttpEntity<>(aluno), Aluno.class).getBody();

        assertAll("validations",
                () -> assertThat(resultAluno).isNotNull(),
                () -> assertThat(resultAluno.getId()).isNotNull(),
                () -> assertThat(resultAluno.getId()).isEqualTo(expectedIdAluno)
        );
    }

    @Test
    public void delete_RemovesAnime_WhenSuccessful() {

        when(alunoRepositoryMock.findById(anyInt()))
                .thenReturn(Optional.of(AlunoCreator.creatingValidAluno()));
        doNothing().when(alunoRepositoryMock).delete(any(Aluno.class));

        ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/alunos/1", HttpMethod.DELETE,
                null, Void.class, 1);

        assertAll("validations",
                () -> assertThat(responseEntity).isNotNull(),
                () -> assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT),
                () -> assertThat(responseEntity.getBody()).isNull()
        );
    }

    @Test
    public void update_SaveUpdatedAnime_WhenSuccessful() {

        Aluno updatedAluno = AlunoCreator.creatingValidUpdatedAluno();
        when(alunoRepositoryMock.save(AlunoCreator.creatingValidAluno()))
                .thenReturn(updatedAluno);

        ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/alunos", HttpMethod.PUT,
                new HttpEntity<>(updatedAluno), Void.class);

        assertAll("validations",
                () -> assertThat(responseEntity).isNotNull(),
                () -> assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT),
                () -> assertThat(responseEntity.getBody()).isNull()
        );
    }
}