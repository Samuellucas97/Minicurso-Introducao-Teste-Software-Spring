package petcc.course.spring.sistemanotas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petcc.course.spring.sistemanotas.model.Disciplina;
import petcc.course.spring.sistemanotas.service.DisciplinaService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {
    private final DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(disciplinaService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        return ResponseEntity.ok(disciplinaService.findById(id));
    }

    @GetMapping(value = "/busca-professsor/{id}")
    public ResponseEntity<?> findByIdProfessor(@PathVariable Integer id){
        return ResponseEntity.ok(disciplinaService.findByIdProfessor(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid Disciplina disciplina){
        return ResponseEntity.ok(disciplinaService.save(disciplina));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        disciplinaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}