package petcc.course.spring.sistemanotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import petcc.course.spring.sistemanotas.model.Disciplina;
import petcc.course.spring.sistemanotas.model.Professor;

import java.util.Optional;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
    Optional<Disciplina> findByProfessor(Professor professor);
}
