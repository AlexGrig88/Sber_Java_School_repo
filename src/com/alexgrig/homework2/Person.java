package com.alexgrig.homework2;

// 2)Реализовать класс  Person и методы marry, divorce , запушить в github
public class Person {
    private final boolean man;
    private final String name;
    private Person spouse;

    public Person(boolean man, String name) {
        this.man = man;
        this.name = name;
    }

    public Person(boolean man, String name, Person spouse) {
        this.man = man;
        this.name = name;
        this.spouse = spouse;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }


    public boolean marry(Person person) {

        boolean getMarried = true;

        // если они уже состоят в браке между собой, то нет смысла жениться
        if (this.spouse == person || person.getSpouse() == this) {
            return false;
        }

        // если они разного пола, то можно вступать в брак при определённых действиях,
        // которые необходимо выполнить
        if (this.man != person.man) {
            if (this.spouse == null && person.getSpouse() == null) { //оба свободны - могут жениться
                getMarried = true;
            } else if (this.spouse != null && person.getSpouse() != null) { //оба в браке - оба разводятся
                this.divorce();
                person.divorce();
                getMarried = true;
            } else if (this.spouse == null && person.getSpouse() != null) { //один разводится
                person.divorce();
                getMarried = true;
            } else if (this.spouse != null && person.getSpouse() == null) { //другой разводится
                this.divorce();
                getMarried = true;
            }
        } else {
            getMarried = false;
        }

        return getMarried;
    }


    public boolean divorce() {
        if (this.spouse != null) {
            this.spouse = null;
            return true;
        }
        return false;
    }
}
