package petcc.course.spring.sistemanotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import petcc.course.spring.sistemanotas.model.Notas;

public interface NotasRepository extends JpaRepository<Notas, Integer> {
}
