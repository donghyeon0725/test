package com.test.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;

@Disabled
// 자바 버전에 따라 조건 걸기
public class Test5 {

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void test_if_jre_ver1() {
        System.out.println("java 8 에서만 실행 됩니다.");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_11)
    void test_if_jre_ver2() {
        System.out.println("java 11 에서만 실행 됩니다.");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_16)
    void test_if_jre_ver3() {
        System.out.println("java 16 에서만 실행 됩니다.");
    }

    @Test
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_11})
    void test_if_jre_ver4() {
        System.out.println("java 8과 11 에서만 실행 됩니다.");
    }

    @Test
    @EnabledOnJre({JRE.JAVA_12, JRE.JAVA_16})
    void test_if_jre_ver5() {
        System.out.println("java 16 과 12 에서만 실행 됩니다.");
    }
}
