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

    public String getName() {
        return name;
    }


    public Person getSpouse() {
        return spouse;
    }

    private void setSpouse(Person spouse) {
        this.spouse = spouse;
    }


    public boolean marry(Person person) {

        //если это один и тот же человек, то нельзя никак
        if (this == person)  return false;
        // если они уже состоят в браке между собой, то нет смысла жениться
        if (this.spouse == person || person.getSpouse() == this) {
            return false;
        }

        boolean getMarried = false;
        // если они разного пола, то можно вступать в брак при определённых действиях,
        // которые необходимо выполнить
        if (this.man != person.man) {
            if (this.spouse == null && person.getSpouse() == null) { //оба свободны - могут жениться
                this.setSpouse(person);
                person.setSpouse(this);
                getMarried = true;
            } else if (this.spouse != null && person.getSpouse() != null) { //оба в браке - оба разводятся
                this.spouse.divorce();
                this.divorce();
                person.getSpouse().divorce();
                person.divorce();
                this.setSpouse(person);
                person.setSpouse(this);
                getMarried = true;
            } else if (this.spouse == null && person.getSpouse() != null) { //один разводится
                person.getSpouse().divorce();
                person.divorce();
                this.setSpouse(person);
                person.setSpouse(this);
                getMarried = true;
            } else if (this.spouse != null && person.getSpouse() == null) { //другой разводится
                this.spouse.divorce();
                this.divorce();
                this.setSpouse(person);
                person.setSpouse(this);
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
