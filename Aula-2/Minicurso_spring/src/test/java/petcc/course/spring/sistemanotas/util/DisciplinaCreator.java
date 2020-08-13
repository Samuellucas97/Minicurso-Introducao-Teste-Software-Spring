package petcc.course.spring.sistemanotas.util;

import petcc.course.spring.sistemanotas.model.Disciplina;

public class DisciplinaCreator {
    public static Disciplina creatingDisciplinaToBeSave() {
        return Disciplina.builder()
                .nome("Teste de Software")
                .professor(ProfessorCreator.creatingValidProfessor())
                .build();
    }

    public static Disciplina creatingValidDisciplina() {
        return Disciplina.builder()
                .id(1)
                .nome("Teste de Software")
                .professor(ProfessorCreator.creatingValidProfessor())
                .build();
    }

    public static Disciplina creatingValidUpdatedADisciplina() {
        return Disciplina.builder()
                .nome("Teste de Software em Spring")
                .professor(ProfessorCreator.creatingValidProfessor())
                .build();
    }
}