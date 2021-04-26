package com.alexgrig.homework1_part2;

//Задача. Реализовать конвертеры температуры. Считаем, что значения будут поступать по шкале Цельсия,
// конвертеры должны преобразовывать значение в свою шкалу.
public class TemperatureConverter {

    private double celsius;

    public TemperatureConverter(double celsius) {
        this.celsius = celsius;
    }

    public void setCelsius(double celsius) {
        this.celsius = celsius;
    }

    public double getCelsius() {
        return celsius;
    }

    public double getFahrenheit() {
        return (celsius * 9.0 / 5) + 32;
    }

    public double getKelvin() {
        return celsius + 273.15;
    }
}
