package com.test.test;

import jdk.jfr.Threshold;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class SlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static long THRESHOLD = 1000L;

    public SlowTestExtension() {}

    public SlowTestExtension(long thresHold) {
        this.THRESHOLD = thresHold;
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        String testClass = context.getRequiredTestClass().getName();
        String testMethod = context.getRequiredTestMethod().getName();
        return context.getStore(ExtensionContext.Namespace.create(testClass, testMethod));
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        getStore(context).put("START_TIME", System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        long end_time = System.currentTimeMillis();
        long execution_time = end_time - (long)getStore(context).get("START_TIME");

        Method method = context.getRequiredTestMethod();
        SlowTest annotation = method.getAnnotation(SlowTest.class);

        if (execution_time > THRESHOLD && annotation == null) {
            System.out.println("기준 시간을 넘는 테스트에는 SlowTest 어노테이션을 붙여야 합니다.");
        }
    }

}
