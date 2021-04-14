package com.alexgrig.homework_collection.task1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* Задача
* Имеется список парка машин Car(String model, String type).
* Необходимо разбить его на списки сгруппированные по type.
 */
public class MainProgram {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        fillList(cars);
        Map<String, List<String>> map = new HashMap<>();


        //формируем карту с уникальными ключами типов машин
        //и пустыми списками для будущего пополнения
        for (int i = 0; i < cars.size(); i++) {
            map.put(cars.get(i).getType(), new ArrayList<>());
        }

        //пополняем списки моделей с одинаковым типом
        for (int i = 0; i < cars.size(); i++) {
            map.get(cars.get(i).getType()).add(cars.get(i).getModel());
        }


        for (Map.Entry<String, List<String>> pair : map.entrySet()) {
            System.out.print("Тип " + pair.getKey());
            System.out.println(": " + pair.getValue());
        }
    }


    public static void fillList(List<Car> list) {
        list.add(new Car("Лада", "Седан"));
        list.add(new Car("Лада", "Хэтчбек"));
        list.add(new Car("Тойота", "Седан"));
        list.add(new Car("Ауди", "Купе"));
        list.add(new Car("Москвич", "Универсал"));
        list.add(new Car("Тойота", "Внедорожник"));
        list.add(new Car("Киа", "Хэтчбек"));
        list.add(new Car("Феррари", "Баллид формула 1"));
        list.add(new Car("Jeep", "Внедорожник"));
        list.add(new Car("Киа", "Седан"));
        list.add(new Car("BMW", "Седан"));
    }
}
