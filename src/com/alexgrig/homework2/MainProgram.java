package com.alexgrig.homework2;

public class MainProgram {
    public static void main(String[] args) {
        Person person1 = new Person(true, "Petr");
        Person person2 = new Person(false, "Olga");
        Person person3 = new Person(true, "Ivan");
        Person person4 = new Person(false, "Ira");


        System.out.println(person1.getName() + " и " + person1.getName() +
                " могут вступить в брак? - " + person1.marry(person1));
        System.out.println(person1.getName() + " и " + person2.getName() +
                " могут вступить в брак? - " + person1.marry(person2));

        System.out.println(person2.getName() + " и " + person4.getName() +
                " могут вступить в брак? - " + person2.marry(person4));
        System.out.println(person3.getName() + " и " + person1.getName() +
                " могут вступить в брак? - " + person1.marry(person3));

        System.out.println("===============================================");
        person1.setSpouse(person2);
        System.out.println(person1.getName() + " и " + person4.getName() +
                " могут вступить в брак? - " + person1.marry(person4));

        System.out.println("===============================================");

        person2.setSpouse(person3);
        person1.setSpouse(person4);

        System.out.println(person2.getName() + " и " + person1.getName() +
                " могут вступить в брак? - " + person1.marry(person2));
        System.out.println(person3.getName() + " и " + person4.getName() +
                " могут вступить в брак? - " + person4.marry(person3));

        person1.setSpouse(person1);
        person1.setSpouse(person3);

    }
}
