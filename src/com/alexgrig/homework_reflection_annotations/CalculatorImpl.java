package com.alexgrig.homework_reflection_annotations;

/*
* Тестовый класс.
* Методы и поля созданы исключительно для выполнения задач,
* и никакой полезной смысловой нагрузки не несут
* */
public class CalculatorImpl implements Calculator {

    private String name;
    private double precision;
    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAY = "WEDNESDAY";
    public static final String THURSDAY = "THURSDAY";
    public static final String FRIDAY = "FRIDAY";
    public static final String SATURDAY = "SATURDAY";
    public static final String SUNDAY = "SUNDAY";


    public CalculatorImpl() {
    }

    @Override
    public int calc(int number) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return number * 5;
    }

    public CalculatorImpl(String name, double precision) {
        this.name = name;
        this.precision = precision;
    }

    public String getName() {
        return name;
    }

    private double getPrecision() {
        return precision;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    private int square(int number) {
        return number * number;
    }

    public int calcSecond(int a, int b, String operation) {
        return 125;
    }

    public static long factorial(int num) {
        long fact = 1;
        for (int i = num; i > 0; i--) {
            fact *= i;
        }
        return fact;
    }
}
