package com.ctw.workstation.learning.simple;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;


class MathOperationsTest {

    public static Stream<Arguments> when_add_two_numbers_correct_result_is_returned() {
        return Stream.of(
                Arguments.of(1, 1, 2),
                Arguments.of(2, 2, 4),
                Arguments.of(0, 0, 0),
                Arguments.of(-2, 1, -1)

        );
    }

    public static Stream<Arguments> when_divide_two_numbers_correct_result_is_returned() {
        return Stream.of(
                Arguments.of(2, 2, 1),
                Arguments.of(4, 2, 2),
                Arguments.of(10, 1, 10)
        );
    }


    @ParameterizedTest
    @MethodSource
    void when_add_two_numbers_correct_result_is_returned(int num1, int num2, int expected) {
        //given
        MathOperations mathOperations = new MathOperations();
        //when
        int result = mathOperations.add(num1, num2);
        //then
        assertThat(result)
                .as("When summing %d and %d, result should be %d", num1, num2, expected)
                .isEqualTo(expected);

    }

    @ParameterizedTest
    @MethodSource
    void when_divide_two_numbers_correct_result_is_returned(int num1, int num2, int expected) {
        // given
        MathOperations mathOperations = new MathOperations();
        // when
        int result =  mathOperations.divide(num1, num2);
        // then
        assertThat(result)
                .as("When dividing %d and %d, result should be %d", num1, num2, expected)
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1})
    void when_divide_by_zero_arithmetic_exception_is_thrown(int num1) {
        // given
        MathOperations mathOperations = new MathOperations();
        // then
        assertThatExceptionOfType(ArithmeticException.class)
                .isThrownBy(() -> mathOperations.divide(num1, 0));
    }

}