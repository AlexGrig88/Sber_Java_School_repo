package com.alexgrig.homework_reflection_annotations.task7;

public class Test {
    private String name;
    private StringBuilder model;
    private int age;
    private double precision;
    private Pet animal;

    public Test(String name, StringBuilder model, int age, double precision, Pet animal) {
        this.model = model;
        this.name = name;
        this.age = age;
        this.precision = precision;
        this.animal = animal;
    }

    public Pet getAnimal() {
        return animal;
    }

    public String getName() {
        return name;
    }

    public StringBuilder getModel() {
        return model;
    }

    public int getAge() {
        return age;
    }

    public double getPrecision() {
        return precision;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModel(StringBuilder model) {
        this.model = model;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public void setAnimal(Pet animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", model=" + model.toString() +
                ", pet=" + animal.getName() +
                ", age=" + age +
                ", precision=" + precision +
                '}';
    }
}
