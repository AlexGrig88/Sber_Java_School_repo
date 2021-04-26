package com.alexgrig.homework_reflection_annotations.task7;

public class MainProgram {

    public static void main(String[] args) {
        StringBuilder model = new StringBuilder("R2D2");

        Test from = new Test("Alex", model ,33, 1.25,  new Pet("кошка Люся"));
        //To to = new To();

        Test to = new Test("NoName", new StringBuilder("Noname"), 0, 0, new Pet("noname"));
        System.out.println("from:");
        System.out.println(from);
        System.out.println();
        System.out.println("to:");
        System.out.println(to);
       try {
           BeanUtils.assign(to, from);
           System.out.println("\n=========================BeanUtils.assign(to, from)==================================\n");
           System.out.println("to:");
           System.out.println(to);
       } catch (IllegalArgumentException ex) {
           ex.printStackTrace();
       }
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

