package petcc.course.spring.sistemanotas.util;

import petcc.course.spring.sistemanotas.model.Professor;

public class ProfessorCreator {
        public static Professor creatingValidProfessor() {
        return Professor.builder()
                .id(1)
                .nome("Junior")
                .cpf("61824635036")
                .email("junior@gmail.com")
                .build();
    }
}
