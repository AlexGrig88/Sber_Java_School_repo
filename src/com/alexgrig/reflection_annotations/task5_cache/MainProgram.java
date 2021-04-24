package com.alexgrig.reflection_annotations.task5_cache;

import com.alexgrig.reflection_annotations.Calculator;
import com.alexgrig.reflection_annotations.CalculatorImpl;

import java.lang.reflect.Proxy;

public class MainProgram {
    public static void main(String[] args) {
        Calculator delegate = new CalculatorImpl();
        Calculator calculator = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CacheInvocationHandler(delegate));
        run(calculator);
    }

    public static void run(Calculator calculator) {
        System.out.println("calculator.calc(10) = " + calculator.calc(10));
        System.out.println("calculator.calc(20) = " + calculator.calc(20));
        System.out.println("calculator.calc(10) = " + calculator.calc(10));
        System.out.println("calculator.calc(20) = " + calculator.calc(20));
        System.out.println("calculator.calc(10) = " + calculator.calc(10));
        System.out.println("calculator.calc(55) = " + calculator.calc(55));
        System.out.println("calculator.calc(2) = " + calculator.calc(20));
        System.out.println("calculator.calc(9) = " + calculator.calc(9));
        System.out.println("calculator.calc(9) = " + calculator.calc(9));
        System.out.println("calculator.calc(2) = " + calculator.calc(20));
        System.out.println("calculator.calc(9) = " + calculator.calc(9));
        System.out.println("calculator.calc(20) = " + calculator.calc(20));

    }
}
