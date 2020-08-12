package petcc.course.spring.sistemanotas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petcc.course.spring.sistemanotas.model.Professor;
import petcc.course.spring.sistemanotas.service.ProfessorService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/professores")
@RequiredArgsConstructor
public class ProfessorController {
    private final ProfessorService professorService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(professorService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        return ResponseEntity.ok(professorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid Professor professor){
        return ResponseEntity.ok(professorService.save(professor));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        professorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}