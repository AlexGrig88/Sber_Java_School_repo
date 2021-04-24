package com.alexgrig.reflection_annotations.task6_logger;

import com.alexgrig.reflection_annotations.Calculator;
import com.alexgrig.reflection_annotations.CalculatorImpl;

import java.lang.reflect.Proxy;

/**
 * Создать свой аннотацию Metric. Реализовать proxy класс PerformanceProxy,
 * который в случае если метод аннотирован Metric будет выводить на консоль
 * время выполнения метода.
 */
public class MainProgram {
    public static void main(String[] args) {
        Calculator delegate = new CalculatorImpl();
        Calculator calculator = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new LoggerInvocationHandler(delegate));
        run(calculator);

    }

    public static void run(Calculator calculator) {
        System.out.println(calculator.calc(10));
        System.out.println(calculator.calc(420));
        System.out.println(calculator.calc(11300));
    }
}
