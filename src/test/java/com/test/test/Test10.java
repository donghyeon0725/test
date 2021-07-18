package com.test.test;

import org.junit.jupiter.api.*;

/**
 * 통합 테스트를 위해서 stateFul 하게 테스트를 진행하는 방법
 *
 * 클래스 마다 1번의 인스턴스 생성 전략
 *
 * 위 전략이기 때문에 junit 입장에서 BeforeAll & afterAll 기능을 구현하기 위해 굳이 해당 메서드가 static일 필요가 없다.
 * 따라서 써주지 않아도 된다.
 * */
@Disabled
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test10 {

    int state = 0;

    @BeforeAll
    void beforeAll() {
        System.out.println("beforeAll");
    }

    @AfterAll
    void afterAll() {
        System.out.println("afterAll");
    }

    @Order(3)
    @Test
    void print1() {
        System.out.println("1 and state : " + state++);
    }

    @Order(2)
    @Test
    void print2() {
        System.out.println("2 and state : " + state++);
    }

    @Order(1)
    @Test
    void print3() {
        System.out.println("3 and state : " + state++);
    }

}
