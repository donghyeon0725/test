package com.test.test;



import org.junit.jupiter.api.*;

/**
 * 테스트 순서만 보장 받고 싶은 경우
 *
 * 메소드 생성 전략이 메소드 마다 1번이기 때문에
 * beforeAll 과 afterAll 을 static 으로 생성하지 않으면 오류
 * */
@Disabled
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test9 {

    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("afterAll");
    }

    @Order(3)
    @Test
    void print1() {
        System.out.println("1");
    }

    @Order(2)
    @Test
    void print2() {
        System.out.println("2");
    }

    @Order(1)
    @Test
    void print3() {
        System.out.println("3");
    }
}
