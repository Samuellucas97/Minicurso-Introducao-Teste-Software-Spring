package petcc.course.spring.sistemanotas.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petcc.course.spring.sistemanotas.model.Aluno;
import petcc.course.spring.sistemanotas.service.AlunoService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/alunos")
@RequiredArgsConstructor
public class AlunoController {
    private final AlunoService alunoService;

    @GetMapping
    @ApiOperation(value = "Endpoint para buscar alunos",
                    notes = "Endpoint usado para buscar todos os alunos presentes no sistema",
                    response = Aluno.class,
                    httpMethod = "GET")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(alunoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        return ResponseEntity.ok(alunoService.findById(id));
    }

    @GetMapping(value = "/busca-nome/{nome}")
    public ResponseEntity<?> findByNome(@PathVariable String nome){
        return ResponseEntity.ok(alunoService.findByName(nome));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid Aluno aluno){
        return ResponseEntity.ok(alunoService.save(aluno));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        alunoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
