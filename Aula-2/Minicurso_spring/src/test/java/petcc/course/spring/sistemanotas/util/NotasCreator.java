package petcc.course.spring.sistemanotas.util;

import petcc.course.spring.sistemanotas.model.Notas;

public class NotasCreator {
    public static Notas creatingNotasToBeSave() {
        return Notas.builder()
                .aluno(AlunoCreator.creatingValidAluno())
                .nota(9.0)
                .disciplina(DisciplinaCreator.creatingValidDisciplina())
                .semestre("2020.2")
                .build();
    }

    public static Notas creatingValidNotas() {
        return Notas.builder()
                .id(1)
                .aluno(AlunoCreator.creatingValidAluno())
                .nota(9.0)
                .disciplina(DisciplinaCreator.creatingValidDisciplina())
                .semestre("2020.2")
                .build();
    }

    public static Notas creatingValidUpdatedADisciplina() {
        return Notas.builder()
                .id(1)
                .aluno(AlunoCreator.creatingValidAluno())
                .nota(7.5)
                .disciplina(DisciplinaCreator.creatingValidDisciplina())
                .semestre("2020.3")
                .build();
    }
}
