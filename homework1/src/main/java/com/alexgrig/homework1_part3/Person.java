package com.alexgrig.homework1_part3;

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

    public String getName() {
        return name;
    }


    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }


    public boolean marry(Person person) {

        // если они уже состоят в браке между собой, то нет смысла жениться
        if (this.spouse == person || person.getSpouse() == this) {
            return false;
        }
        //если они одного пола, то нельзя вступить в традиционный брак
        if (this.man == person.man) return false;

        // если они разного пола, то можно вступать в брак при определённых действиях,
        // которые необходимо выполнить

        if (this.spouse != null && person.getSpouse() != null) { //оба в браке - оба разводятся
            this.divorce();
            person.divorce();
        } else if (this.spouse == null && person.getSpouse() != null) { //один разводится
            person.divorce();
        } else if (this.spouse != null && person.getSpouse() == null) { //другой разводится
            this.divorce();
        }

        return true;
    }


    public boolean divorce() {
        if (this.spouse != null) {
            this.spouse.spouse = null;
            this.spouse = null;
            return true;
        }
        return false;
    }
}
