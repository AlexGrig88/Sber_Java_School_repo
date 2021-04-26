package com.alexgrig.homework_generics.task1;


import java.util.Map;

// Задача. Параметризовать CountMap (из репозитория выше) и реализовать его.
public interface CountMap<T> {

    void add(T o);

    int getCount(T o);

    int remove(T o);

    int size();

    void addAll(CountMap<? extends T> source);

    Map<T, Integer> toMap();

    void toMap(Map<T, Integer> destination);
}
