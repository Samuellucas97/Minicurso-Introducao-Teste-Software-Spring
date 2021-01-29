package petcc.course.spring.sistemanotas.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petcc.course.spring.sistemanotas.model.Disciplina;
import petcc.course.spring.sistemanotas.service.DisciplinaService;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping(value = "/disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {
    private final DisciplinaService disciplinaService;

    @GetMapping
    @ApiOperation(value = "Busca todas disciplinas")
    public ResponseEntity<List<Disciplina>> findAll(){
        return ResponseEntity.ok(this.disciplinaService.findAll());
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Busca disciplina pelo id")
    public ResponseEntity<Disciplina> findById(@PathVariable Integer id){
        return ResponseEntity.ok(this.disciplinaService.findById(id));
    }

    @GetMapping(value = "/busca-professsor/{id}")
    @ApiOperation(value="Busca disciplina pelo id do professor")
    public ResponseEntity<Disciplina> findByIdProfessor(@PathVariable Integer id){
        return ResponseEntity.ok(this.disciplinaService.findByIdProfessor(id));
    }

    @PostMapping
    @ApiOperation(value="Salva uma nova disciplina")
    public ResponseEntity<Disciplina> save(@RequestBody @Valid Disciplina disciplina){
        return ResponseEntity.ok(this.disciplinaService.save(disciplina));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value="Remove uma disciplina")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        this.disciplinaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    @ApiOperation(value="Atualiza uma disciplina")
    public ResponseEntity<Void> update(@RequestBody @Valid Disciplina disciplina) {
        this.disciplinaService.update(disciplina);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}