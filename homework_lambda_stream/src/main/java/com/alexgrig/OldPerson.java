package com.alexgrig;

public class OldPerson {
    private String fName;
    private int age;

    public OldPerson(String fName, int age) {
        this.fName = fName;
        this.age = age;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "OldPerson{" +
                "fName='" + fName + '\'' +
                ", age=" + age +
                '}';
    }
}
