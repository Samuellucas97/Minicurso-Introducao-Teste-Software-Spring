package petcc.course.spring.sistemanotas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import petcc.course.spring.sistemanotas.exception.ResourceNotFoundException;
import petcc.course.spring.sistemanotas.model.Disciplina;
import petcc.course.spring.sistemanotas.model.Professor;
import petcc.course.spring.sistemanotas.repository.DisciplinaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplinaService {
    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorService professorService;

    public List<Disciplina> findAll(){
        return disciplinaRepository.findAll();
    }

    public Disciplina findByIdProfessor(Integer id){
        Professor professor = professorService.findById(id);
        return this.findProfessorOfDisciplinaOrThrowNotFound(professor);
    }

    public Disciplina findById(Integer id){
        return this.findDisciplinaOrThrowNotFound(id);
    }

    public Disciplina save(Disciplina disciplina){
        return this.disciplinaRepository.save(disciplina);
    }

    public void delete(Integer id){
        this.disciplinaRepository.delete(this.findDisciplinaOrThrowNotFound(id));
    }

    public void update(Disciplina disciplina) { this.disciplinaRepository.save(disciplina);}

    private Disciplina findDisciplinaOrThrowNotFound(Integer id) {
        return this.disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada"));
    }

    private Disciplina findProfessorOfDisciplinaOrThrowNotFound(Professor professor) {
        return this.disciplinaRepository.findByProfessor(professor)
                .orElseThrow(() -> new ResourceNotFoundException("Professor da disciplina não encontrado"));
    }

}