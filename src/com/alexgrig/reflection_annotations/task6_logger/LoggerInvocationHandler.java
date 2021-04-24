package com.alexgrig.reflection_annotations.task6_logger;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggerInvocationHandler implements InvocationHandler {
    private Object delegate;

    public LoggerInvocationHandler(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Metric.class)) {
            return method.invoke(delegate, args);
        }
        System.out.println("Метод: " + method.getName() + " вызван");
        Long before = System.currentTimeMillis();
        Object invoke = method.invoke(delegate, args);
        Long after = System.currentTimeMillis();
        System.out.println("Метод: " + method.getName() + " завершен. Время: " + (after - before));
        return invoke;
    }
}
