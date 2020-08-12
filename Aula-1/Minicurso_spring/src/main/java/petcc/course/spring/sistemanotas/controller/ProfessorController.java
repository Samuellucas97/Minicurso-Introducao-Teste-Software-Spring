package petcc.course.spring.sistemanotas.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petcc.course.spring.sistemanotas.model.Professor;
import petcc.course.spring.sistemanotas.service.ProfessorService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/professores")
@RequiredArgsConstructor
public class ProfessorController {
    private final ProfessorService professorService;

    @GetMapping
    @ApiOperation(value = "Busca todos professores")
    public ResponseEntity<List<Professor>> findAll(){
        return ResponseEntity.ok(this.professorService.findAll());
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Busca professor pelo id")
    public ResponseEntity<Professor> findById(@PathVariable Integer id){
        return ResponseEntity.ok(this.professorService.findById(id));
    }

    @PostMapping
    @ApiOperation(value="Salva um novo professor")
    public ResponseEntity<Professor> save(@RequestBody @Valid Professor professor){
        return ResponseEntity.ok(this.professorService.save(professor));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value="Remove um professor")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        this.professorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    @ApiOperation(value="Atualiza um professor")
    public ResponseEntity<Void> update(@RequestBody @Valid Professor professor) {
        this.professorService.update(professor);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}