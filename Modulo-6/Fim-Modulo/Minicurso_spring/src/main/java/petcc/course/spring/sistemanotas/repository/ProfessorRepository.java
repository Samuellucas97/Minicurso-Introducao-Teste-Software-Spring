package petcc.course.spring.sistemanotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import petcc.course.spring.sistemanotas.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
