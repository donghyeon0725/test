package com.test.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * 확장팩을 사용하는 클래스
 *
 * 선언적으로 확장팩을 사용하는 방법
 * */
@Disabled
@ExtendWith(SlowTestExtension.class)
public class Test12 {

    @Test
    void test1() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println("test1");
    }

    @SlowTest
    void test2() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println("test2");
    }

}
