package petcc.course.spring.sistemanotas.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petcc.course.spring.sistemanotas.model.Aluno;
import petcc.course.spring.sistemanotas.service.AlunoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/alunos")
@RequiredArgsConstructor
public class AlunoController {
    private final AlunoService alunoService;

    @GetMapping
    @ApiOperation(value = "Busca todos alunos")
    public ResponseEntity<List<Aluno>> findAll(){
        return ResponseEntity.ok(alunoService.findAll());
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Busca aluno pelo id")
    public ResponseEntity<Aluno> findById(@PathVariable Integer id){
        return ResponseEntity.ok(alunoService.findById(id));
    }

    @GetMapping(value = "/busca-nome/{nome}")
    @ApiOperation(value = "Busca aluno por nome")
    public ResponseEntity<List<Aluno>> findByNome(@PathVariable String nome){
        return ResponseEntity.ok(alunoService.findByName(nome));
    }

    @PostMapping
    @ApiOperation(value="Salva um novo aluno")
    public ResponseEntity<Aluno> save(@RequestBody @Valid Aluno aluno){
        return ResponseEntity.ok(alunoService.save(aluno));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value="Remove um aluno")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        alunoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    @ApiOperation(value="Atualiza um aluno")
    public ResponseEntity<Void> update(@RequestBody @Valid Aluno aluno) {
        alunoService.update(aluno);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}