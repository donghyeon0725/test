package com.test.test;

import lombok.With;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
// 반복 테스트 전문화
public class Test8 {
    /**
     * 매개변수가 있는 테스트 케이스
     * 빈 값으로 테스트 케이스
     * null 값으로 테스트 케이스
     * */
    @DisplayName("매개 변수를 이용한 반복 테스트")
    @ParameterizedTest(name = "{displayName}, {index} massage={0}")
    @ValueSource(strings = {"이연복", "김강민", "안녕해", "있네요"})
    @EmptySource
    @NullSource
    void test_parameter_repeat(String message) {
        System.out.println(message);
    }


    /**
     * 심플 매개변수 컨버터를 구현하여 1개의 매개변수를 원하는 객체로 생성
     * */
    @ParameterizedTest
    @DisplayName("심플 매개변수 컨버터를 이용해서 매개변수 주입")
    @CsvSource({"이름1", "이름2"})
    void test_with_converter_1(@ConvertWith(PersonConverter.class) Person person) {
        System.out.println(person.getName());
    }

    static class PersonConverter extends SimpleArgumentConverter {

        @Override
        protected Object convert(Object o, Class<?> aClass) throws ArgumentConversionException {
            assertEquals(Person.class, aClass, () -> "오직 Person class 만 변환이 가능합니다");
            return new Person((String)o);
        }
    }

    /**
     * 둘 이상의 매개변수를 테스트
     * */
    @ParameterizedTest
    @DisplayName("심플 매개변수 컨버터를 이용해서 매개변수 주입")
    @CsvSource({"이름1, 7", "'이름 2', 12"})
    void test_with_converter_2(ArgumentsAccessor argumentsAccessor) {
        System.out.println(argumentsAccessor.get(0) + ", " + argumentsAccessor.get(1));
    }

    /**
     * 둘 이상의 매개변수를 원하는 객체로 받는 방법
     *
     * ArgumentsAggregator 인터페이스를 구현하여야 한다.
     * AggregateWith 으로 받아야 한다.
     * */
    @ParameterizedTest
    @DisplayName("심플 매개변수 컨버터를 이용해서 매개변수 주입")
    @CsvSource({"이름1, 7", "'이름 2', 12"})
    void test_with_converter_3(@AggregateWith(PersonAggregator.class) Person person) {
        System.out.println(person);
    }

    /**
     * inner 클래스는 static
     * 외부 클래스는 무조건 public 이어야 한다.
     * */
    static class PersonAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            return new Person(argumentsAccessor.getString(0), argumentsAccessor.getInteger(1));
        }
    }



}
