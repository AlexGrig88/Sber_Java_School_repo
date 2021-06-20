package com.alexgrig;

import java.lang.reflect.Proxy;

public class MainProgram {
    public static void main(String[] args) {
        Calculable delegate = new Calculator();
        Calculable calculable = (Calculable) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CacheInvocationHandler(delegate));
        run(calculable);
    }


    public static void run(Calculable calculable) {
        System.out.println(calculable.fibonachi(6));
        System.out.println(calculable.fibonachi(8));
        System.out.println(calculable.fibonachi(6));
        System.out.println(calculable.fibonachi(12));
    }

}
