package com.alexgrig;

import java.io.Serializable;

public class Person implements Serializable {
    private String lastName;
    private int age;

    public Person(String lastName, int age) {
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
