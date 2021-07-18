package com.test.test;

import org.junit.jupiter.api.*;

@Disabled
// 이름 정하기
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Test2 {

    @Test
    void print_stack_trace1() {
        System.out.println("printStackTrace");
    }

    @Test
    @DisplayName("📌 이모지를 이용해 이름 만들기")
    void print_stack_trace2() {

    }
}
