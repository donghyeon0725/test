package com.test.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Disabled
// 태깅 & 필터링
public class Test6 {

    /**
     * 인텔리제이 툴을 사용하는 방법
     * Run Configuration - Type 을 Tags 로 변경 후, slow 입력
     * */
    @Test
    @Tag("fast")
    void fast() {
        System.out.println("fast");
    }

    /**
     * 빌드 툴중 maven 을 이용하는 방법
     *
     * mvnw test -P test_tag_1
     * id 를 test_tag_1 으로 해서 test를 수행하면 됨
     *
     * */
    @Test
    @Tag("slow")
    void slow() {
        System.out.println("slow");
    }

    @FastTest
    void fast_annotation() {
        System.out.println("fast_annotation");
    }
}
