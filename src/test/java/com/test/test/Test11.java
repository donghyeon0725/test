package com.test.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;


/**
 * 설정 파일을 테스트 하기 위한 클래스 입니다.
 * 설정 파일의 주석을 하나씩 풀어가며 테스트 해보세요
 * */
@Disabled
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Test11 {
    int state = 0;

    @Disabled
    @Test
    void print_all_1() {
        System.out.println(state++);
    }

    @Test
    void print_all_2() {
        System.out.println(state++);
    }
}
