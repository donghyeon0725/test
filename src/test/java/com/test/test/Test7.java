package com.test.test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

// 반복 테스트
@Disabled
public class Test7 {

    @DisplayName("반복 테스트")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void test_repeat(RepetitionInfo repetitionInfo) {
        System.out.println("테스트 , " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("매개 변수를 이용한 반복 테스트")
    @ParameterizedTest(name = "{displayName}, {index} massage={0}")
    @ValueSource(strings = {"이연복", "김강민", "안녕해", "있네요"})
    void test_parameter_repeat(String message) {
        System.out.println(message);
    }

}
