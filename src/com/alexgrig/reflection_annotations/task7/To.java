package com.alexgrig.reflection_annotations.task7;

public class To {
    private String name;
    private String model;
    private int age;
    private double precision;
    private Animal animal = new Animal("Empty");

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "To{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", animal='" + animal.getName() + '\'' +
                ", age=" + age +
                ", precision=" + precision +
                '}';
    }
}
