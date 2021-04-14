package com.alexgrig.homework_generics.task1;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<T> implements  CountMap<T>{

    private final int counter = 1;
    private Map<T, Integer> mapCounter;

    public CountMapImpl() {
        mapCounter = new HashMap<>();
    }

    // добавляет элемент в этот контейнер.
    @Override
    public void add(T o) {
        if (mapCounter.containsKey(o)) {
            mapCounter.replace(o, mapCounter.get(o) + 1);
        } else {
            mapCounter.put(o, counter);
        }
    }

    //Возвращает количество добавлений данного элемента
    @Override
    public int getCount(T o) {
        if (!mapCounter.containsKey(o)) return 0;
        return mapCounter.get(o);
    }

    //Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
    @Override
    public int remove(T o) {
        return mapCounter.remove(o);
    }

    //количество разных элементов
    @Override
    public int size() {
        return mapCounter.size();
    }

    //Добавить все элементы из source в текущий контейнер,
    // при совпадении ключей,     суммировать значения
    @Override
    public void addAll(CountMap<? extends T> source) {
        HashMap<T, Integer> donor = (HashMap<T, Integer>) source.toMap();
        for (Map.Entry<T, Integer> pair : donor.entrySet()) {
            if (mapCounter.containsKey(pair.getKey()))
                mapCounter.replace(pair.getKey(), mapCounter.get(pair.getKey()) + pair.getValue());
            else
                mapCounter.put(pair.getKey(), pair.getValue());
        }
    }

    //Вернуть java.util.Map. ключ - добавленный элемент,
    // значение - количество его добавлений
    @Override
    public Map<T, Integer> toMap() {
        Map<T, Integer> cloneMap = new HashMap<>();
        for (Map.Entry<T, Integer> pair : mapCounter.entrySet()) {
            cloneMap.put(pair.getKey(), pair.getValue());
        }
        return cloneMap;
    }

    //Тот же самый контракт как и toMap(), только всю информацию записать в destination
    @Override
    public void toMap(Map<T, Integer> destination) {
       for (Map.Entry<T, Integer> pair : mapCounter.entrySet()) {
           destination.put(pair.getKey(), pair.getValue());
       }
    }
}
