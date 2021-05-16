package com.alexgrig;

public class Person {

    private String firstName;
    private int age;
    private boolean isStudent;
    private double weight;

    public Person(String firstName, int age, boolean isStudent, double weight) {
        this.firstName = firstName;
        this.age = age;
        this.isStudent = isStudent;
        this.weight = weight;
    }

    public Person(String firstName, int age) {
        this.firstName = firstName;
        this.age = age;
    }

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", age=" + age +
                ", isStudent=" + isStudent +
                ", weight=" + weight +
                '}';
    }
}
