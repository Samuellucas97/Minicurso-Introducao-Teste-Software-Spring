package petcc.course.spring.sistemanotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petcc.course.spring.sistemanotas.model.Aluno;

import java.util.List;

public interface AlunoRepository  extends JpaRepository<Aluno, Integer> {
    List<Aluno> findByNome(String nome);
}
