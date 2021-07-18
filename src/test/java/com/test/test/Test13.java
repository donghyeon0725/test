package com.test.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

/**
 * 프로그래밍 적으로 확장팩을 사용하는 방법
 * (내부 필드)
 *
 * static 키워드는 필수입니다.
 *
 * 이 후, RegisterExtension 어노테이션으로 등록해주어야 합니다.
 * */
public class Test13 {
    @RegisterExtension
    static final SlowTestExtension slowTestExtension = new SlowTestExtension(2000L);

    @Test
    void test1() throws InterruptedException {
        Thread.sleep(2005L);
        System.out.println("test1");
    }

    @SlowTest
    void test2() throws InterruptedException {
        Thread.sleep(2005L);
        System.out.println("test2");
    }
}
