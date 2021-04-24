package com.alexgrig.reflection_annotations.task7;

public class MainProgram {

    public static void main(String[] args) {
        StringBuilder model = new StringBuilder("R2D2");
        Pet pet = new Pet("кошка Люся");
        From from = new From("Alex", model ,33, 1.25, pet);
        To to = new To();
        System.out.println(from);
        System.out.println();
        System.out.println(to);
        BeanUtils.assign(to, from);
        System.out.println("\n=========================BeanUtils.assign(to, from)==================================\n");

        System.out.println(to);
    }
}

class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
class Pet extends Animal {
    public Pet(String name) {
        super(name);
    }
}
