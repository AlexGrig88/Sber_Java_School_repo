package com.alexgrig.homework_reflection_annotations.task7;

public class From {
    private String name;
    private StringBuilder model;
    private int age;
    private double precision;
    private Pet animal;

    public From(String name, StringBuilder model, int age, double precision, Pet animal) {
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

    @Override
    public String toString() {
        return "From{" +
                "name='" + name + '\'' +
                ", model=" + model.toString() +
                ", pet=" + animal.getName() +
                ", age=" + age +
                ", precision=" + precision +
                '}';
    }
}
