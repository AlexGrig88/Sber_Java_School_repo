package com.alexgrig.homework2;

public class MainProgram {
    public static void main(String[] args) {
        Person person1 = new Person(true, "Petr");
        Person person2 = new Person(false, "Olga");
        Person person3 = new Person(true, "Ivan");
        Person person4 = new Person(false, "Ira");


        System.out.println("Они могут вступить в брак? - " + person1.marry(person2));
        System.out.println("Они могут вступить в брак? - " + person2.marry(person4));

        person1.setSpouse(person2);
        System.out.println("Они могут вступить в брак? - " + person2.marry(person1));
        System.out.println("Они могут вступить в брак? - " + person1.marry(person4));

    }
}
