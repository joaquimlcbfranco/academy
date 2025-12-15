package com.ctw.workstation.learning.simple;

import org.assertj.core.api.Assertions;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HelloAcademyTest {

    Logger logger = Logger.getLogger(HelloAcademyTest.class.getName());
    private HelloAcademy helloAcademy = new HelloAcademy();

    private void showCount() {
        System.out.printf("Counter %s\n", helloAcademy.counter);
    }

    @BeforeAll
    void setupAll() {
        System.out.println("Setting up tests...");
        helloAcademy.initialize();
    }

    @BeforeEach
    void setupEach() {
        System.out.println("Starting test...");
    }

    @AfterEach
    void printCounterInfo() {
        showCount();
    }

    @AfterAll
    void tearDown() {
        System.out.println("All tests finished.");
    }

    public static Stream<Arguments> when_say_hello_with_name_non_null_valid_hello_greeting_is_returned() {
        return Stream.of(
                Arguments.of("Rennan", "Hello Rennan"),
                Arguments.of("Alice", "Hello Alice"),
                Arguments.of("Bob", "Hello Bob"),
                Arguments.of(null, "Hello"),
                Arguments.of("", "Hello "),
                Arguments.of(" ", "Hello  ")
        );
    }

    @ParameterizedTest
    @MethodSource
    //@ValueSource(strings = {"Rennan", "Alice", "Bob", " ", "1234"})
    @DisplayName("when say hello with name non null valid hello greeting is returned")
    void when_say_hello_with_name_non_null_valid_hello_greeting_is_returned(String name, String expected) {
        //given
        //when
        String result = helloAcademy.sayHello(name);
        //then

        // assertj assert
        assertThat(result)
                .as("When providing name %s, the greeting should be hello %s", name, expected)
                .isNotNull()
                .as("When saying hello to %s, the greeting should be %s", name, expected)
                .isEqualTo(expected);

        // junit5 assert
//        assertAll("Saying hello with non-null name",
//                () -> assertNotNull(result, "When providing name %s, the greeting should be hello %s".formatted(name, name)),
//                () -> assertEquals(expected, result, "When saying hello to %s, the greeting should be %s".formatted(name, expected))
//        );

    }

    @Test
    void when_say_hello_with_null_name_exception_should_be_thrown() {
        //when
        String name = null;
//        String result = helloAcademy.sayHello(name);
        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> helloAcademy.sayHello(name))
                .withMessage("Name cannot be null");

        Assertions.assertThatThrownBy(() -> helloAcademy.sayHello(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Name cannot be null");

    }

    @Test
    void a(){

    }

    @Test
    void b(){
    }

    @Order(1)
    @Test
    void c(){
    }
}