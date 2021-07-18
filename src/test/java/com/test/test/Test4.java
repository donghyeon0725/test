package com.test.test;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.lang.annotation.Retention;

import static org.junit.jupiter.api.Assumptions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;
@Disabled
/**
 * 테스트 실행 가능 조건을 달아주기
 *
 * static org.junit.jupiter.api.Assumptions api 들을 import 해야함
 * */
public class Test4 {

    /**
     * assumeTrue 조건을 만족하지 않으면 실행되지 않음
     * */
    @Test
    void test_if_variable_condition_true() {
        String name = "name";
        assumeTrue("name".equals(name));

        assertEquals(7, 5, () -> "위 조건을 만족하지 않으면 이 테스트는 실행되지 않습니다.");
    }

    /**
     * assumeTrue 조건을 만족하지 않으면 실행되지 않음
     * */
    @Test
    void test_if_variable_condition_true_with_assuming_api() {
        String name = "name";
        assumingThat("name".equals(name),
                () -> assertEquals(7, 5, () -> "앞의 조건을 만족하지 않으면 이 테스트는 실행되지 않습니다.")
        );
    }


    /**
     * 환경 변수를 가져와 비교하기
     * */
    @Test
    void test_if_env_true_1() {
        String env = System.getenv("TEMP");
        String path = "C:\\Users\\ehdgu\\AppData\\Local\\Temp";

        assumeTrue(env.equals(path));

        assertEquals(1, 2, () -> "환경 변수가 동일하지 않으면 실행되지 않습니다.");
    }


    /**
     * 어노테이션으로 환경 변수 비교하기
     *
     * 단, 전체 테스트를 돌릴 때만 이 어노테이션 유효하다.
     * */
    @EnabledIfEnvironmentVariable(named = "TEMP", matches = "C:\\Users\\ehdgu\\AppData\\Local\\Temp")
    @Test
    void test_if_env_true_2() {
        assertEquals(1, 2, () -> "환경 변수가 동일하지 않으면 실행되지 않습니다.");
    }

    /**
     * 어노테이션으로 OS 비교하기
     *
     * 단, 전체 테스트를 돌릴 때만 이 어노테이션 유효하다.
     * */
    @EnabledOnOs(OS.WINDOWS)
    @Test
    void test_if_os_true_1() {
        assertEquals(1, 2, () -> "window 에서만 실행 됩니다.");
    }

    /**
     * 어노테이션으로 OS 비교하기
     *
     * 단, 전체 테스트를 돌릴 때만 이 어노테이션 유효하다.
     * */
    @EnabledOnOs(OS.MAC)
    @Test
    void test_if_os_true_2() {
        assertEquals(1, 2, () -> "mac 에서만 실행 됩니다.");
    }


    /**
     * 어노테이션으로 OS 비교하기
     *
     * 단, 전체 테스트를 돌릴 때만 이 어노테이션 유효하다.
     * */
    @EnabledOnOs({OS.MAC, OS.LINUX})
    @Test
    void test_if_os_true_3() {
        assertEquals(1, 2, () -> "mac 와 linux 에서만 실행 됩니다.");
    }


    /**
     * 어노테이션으로 OS 비교하기
     *
     * 단, 전체 테스트를 돌릴 때만 이 어노테이션 유효하다.
     * */
    @EnabledOnOs({OS.MAC, OS.WINDOWS})
    @Test
    void test_if_os_true_4() {
        assertEquals(1, 2, () -> "mac 과 window 에서만 실행 됩니다.");
    }
}
