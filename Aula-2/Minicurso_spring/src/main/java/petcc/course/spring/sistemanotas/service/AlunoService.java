package petcc.course.spring.sistemanotas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import petcc.course.spring.sistemanotas.exception.ResourceNotFoundException;
import petcc.course.spring.sistemanotas.model.Aluno;
import petcc.course.spring.sistemanotas.repository.AlunoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository alunoRepository;

    public List<Aluno> findAll(){
        return this.alunoRepository.findAll();
    }

    public List<Aluno> findByName(String name){
        return this.alunoRepository.findByNome(name);
    }

    public Aluno findById(Integer id){
        return this.findAlunoOrThrowNotFound(id);
    }

    public Aluno save(Aluno aluno){
        return this.alunoRepository.save(aluno);
    }

    public void delete(Integer id){
        this.alunoRepository.delete(this.findAlunoOrThrowNotFound(id));
    }

    private Aluno findAlunoOrThrowNotFound(Integer id) {
        return this.alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
    }
}