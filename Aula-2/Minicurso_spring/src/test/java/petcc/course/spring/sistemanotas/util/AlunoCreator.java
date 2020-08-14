package petcc.course.spring.sistemanotas.util;

import petcc.course.spring.sistemanotas.model.Aluno;

public class AlunoCreator {
    public static Aluno creatingAlunoToBeSaved() {
        return Aluno.builder()
                .nome("Junior")
                .cpf("61824635036")
                .curso("Tecnologia da Informação")
                .email("abc@gmail.com")
                .build();
    }

    public static Aluno creatingValidAluno() {
        return Aluno.builder()
                .id(1)
                .nome("Junior")
                .cpf("61824635036")
                .curso("Tecnologia da Informação")
                .email("junior@gmail.com")
                .build();
    }

    public static Aluno creatingValidUpdatedAluno() {
        return Aluno.builder()
                .id(1)
                .nome("Junior Fernandes")
                .cpf("61824635036")
                .curso("Tecnologia da Informação")
                .email("junior@gmail.com")
                .build();
    }
}
