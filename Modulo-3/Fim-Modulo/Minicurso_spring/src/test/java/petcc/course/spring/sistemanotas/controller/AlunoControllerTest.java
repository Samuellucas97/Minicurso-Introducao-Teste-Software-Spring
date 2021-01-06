package petcc.course.spring.sistemanotas.controller;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AlunoControllerTest {

    @Test
    void test() {}

    @Test
    void listAll_ReturnEmptyList_WhenThereIsNotAluno() {

        assertAll(
            () -> assertEquals(1, 2),
            () -> assertEquals(1, 1),
            () -> assertEquals(2, 3),
            () -> assertEquals(3, 3)
        );

    }
}