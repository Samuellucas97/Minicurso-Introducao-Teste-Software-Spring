package petcc.course.spring.sistemanotas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import petcc.course.spring.sistemanotas.exception.ResourceNotFoundException;
import petcc.course.spring.sistemanotas.model.Disciplina;
import petcc.course.spring.sistemanotas.model.Professor;
import petcc.course.spring.sistemanotas.repository.ProfessorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public List<Professor> findAll(){
        return this.professorRepository.findAll();
    }

    public Professor findById(Integer id){
        return this.findProfessorOrThrowNotFound(id);
    }

    public Professor save(Professor professor){
        return this.professorRepository.save(professor);
    }

    public void delete(Integer id){
        this.professorRepository.deleteById(id);
    }

    public void update(Professor professor) { this.professorRepository.save(professor); }

    private Professor findProfessorOrThrowNotFound(Integer id) {
        return this.professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));
    }
}