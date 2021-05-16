package com.alexgrig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class MainProgram {
    public static void main(String[] args) {

        List<Person> people = new ArrayList<>();

        people.add(new Person("Ivan", 39, false, 82.5));
        people.add(new Person("Victoria", 19, true, 64));
        people.add(new Person("Pol", 18, true,73));
        people.add(new Person("Olga", 41, false, 59));
        people.add(new Person("Dima", 54, false, 92.5));
        people.add(new Person("Nikolay", 27, false, 84));
        people.add(new Person("Lena", 14, true, 100));

        Map<String, OldPerson> map = Streams.of(people)
                .filter(p -> p.getAge() > 20)
                .transform(p -> new OldPerson(p.getFirstName(), p.getAge() + 30))
                .limit(2)
                .toMap(p -> p.getfName(), p -> p);

        map.forEach((k, v) -> System.out.println( k + " ; " + v));


    }
}
