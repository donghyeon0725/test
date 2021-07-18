package com.test.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

/**
 * assertNotNull 처럼 메소드를 바로 불러와 사용하기 위해서는
 * org.junit.jupiter.api.Assertions 의 api 를
 * static import 하여야 한다.
 *
 * import static org.junit.jupiter.api.Assertions.*;
 *
 *
 *
 * assertThat 을 테스트 하기 위해서는
 * org.assertj.core.api.Assertions.assertThat 을
 * static import 하여야 한다.
 *
 * import static org.hamcrest.MatcherAssert.*;
 * */
@Disabled
public class Test3 {

    @Disabled
    @DisplayName("not null test 1")
    @Test
    void not_null_test1() {
        Person person = null;
        assertNotNull(person);
    }


    @DisplayName("not null test 2")
    @Test
    void not_null_test2() {
        Person person = new Person("이름", 15, PersonStatus.HEALTHY);
        assertNotNull(person);
    }

    /**
     * 람다로 메세지를 넘김으로써,
     * 문자열 연산의 비용을 줄일 수 있다.
     *
     * 에러시 다음과 같은 메세지
     * org.opentest4j.AssertionFailedError: 사람은 건강해야 합니다. ==>
     * Expected :NORMAL
     * Actual   :HEALTHY
     * */
    @DisplayName("equals test 1")
    @Test
    void equals_test1() {
        Person person = new Person("이름", 15, PersonStatus.HEALTHY);
        assertEquals(PersonStatus.HEALTHY, person.getStatus(), () -> "사람은 건강해야 합니다.");
    }

    /**
     * exception 이 발생하는지 확인이 가능하고, Exception 을 받아서 내용 또한 확인이 가능하다.
     * */
    @DisplayName("Exception Equals Test 1")
    @Test
    void exception_equals_test1() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> ExceptionBox.throwIllegalStateException());
        System.out.println(exception.getMessage());
        assertEquals("IllegalStateException", exception.getMessage(), () -> "메세지가 예상한 값과 다릅니다.");
    }


    /**
     * 시간 안에 테스트가 완료 되는지 확인
     *
     * 시간은 다음과 같이 다양하게 사용이 가능
     * Duration.ofSeconds()
     * Duration.ofMinutes()
     * Duration.ofMillis()
     * */
    @DisplayName("Timeout Test 1")
    @Test
    void timeout_test1() {
        assertTimeout(Duration.ofSeconds(1), () -> {
            Integer age = 7;
            Thread.sleep(100);
        }, () -> "기준 시간 안에 테스트를 완료하지 못 했 습니다.");
    }

    /**
     * 아래는 시간이 지나면 바로 테스트를 종료함
     *
     * ****************************
     * 주의할 점
     *
     * 만약 테스트 코드가 ThreadLocal 을 사용하는 경우
     * 스프링은 트랜젝션 처리는 threadLocal 이 기본 전략인데 다른 스레드에서 트랜젝션 공유가 안되는 것이 특징적이다.
     * 따라서 만들어 놓은 테스트가 원하는대로 동작하지 않을 수 있는데
     *
     * 가령 예를 들어서 스프링이 사용하고 있는 트랜젝션의 기본 전략은 rollback 이다.
     * 따라서 정상적으로 스프링을 통해 DB에 insert 를 한다면, rollback 설정이 된 트랜젝션을 가져와 사용하겠지만
     * 테스트 코드에서는 설정된 트랜젝션이 아닌 다른 트랜젝션을 얻어와 사용할 수 있다.
     *
     * 따라서 중간에 테스트가 중간 종료되어 rollBack 코드를 동작 시키기 전에 끝난다면,
     * DB 에 값이 rollback 되지 않고 반영되어 버릴 수 있다.
     *
     * 따라서 테스트가 트랜젝션과 연관이 있다면 assertTimeoutPreemptively  의 사용은 자제한다.
     * */
    @DisplayName("Timeout Preemptively Test 1")
    @Test
    void timeout_preemptively_test1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            Integer age = 7;
            Thread.sleep(100);
        }, () -> "기준 시간 안에 테스트 완료를 하지 못 했으므로 즉각 종료 합니다.");
    }

    /**
     * 글을 읽는 것처럼 작성을 위한
     * assertThat
     * */
    @DisplayName("assert that 1")
    @Test
    void assert_that1() {
        Person person = new Person("이름", 15, PersonStatus.HEALTHY);
        assertThat(person.getStatus()).isEqualByComparingTo(PersonStatus.HEALTHY);
    }


    /**
     * 테스트를 한번에 진행하는 방법
     * */
    @DisplayName("assert all 1")
    @Test
    void assert_all_1() {
        assertAll(
                () -> assertEquals(4, 4),
                () -> assertTrue(true),
                () -> assertTrue(7 > 6)
        );
    }

}
