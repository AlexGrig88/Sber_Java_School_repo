package com.alexgrig.homework_generics.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionUtils {

    public static<T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static<T> List<T> newArrayList() {
        List<T> list = new ArrayList<>();
        return list;
    }

    public static<T> int indexOf(List<? super T> source, T o) {
        return source.indexOf(o);
    }

    public static<T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static<T> List<T> limit(List<? extends T> source, int size) {
        List<T> limitList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            limitList.add(source.get(i));
        }
        return limitList;
    }

    public static<T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static<T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static<T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        for (T element : c2) {
            if (c1.contains(element)) return true;
        }
        return false;
    }

    public static<T extends Comparable> List<T> range(List<T> list, T min, T max) {
        List<T> result = new ArrayList<>();
        for (T element : list) {
            if (element.compareTo(min) >= 0 && element.compareTo(max) <= 0) {
                result.add(element);
            }
        }
        return result;
    }

    public static<T> List<T> range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        List<T> result = new ArrayList<>();
        for (T element : list) {
            if (comparator.compare(element, min) >= 0 && comparator.compare(element, max) <= 0) {
                result.add(element);
            }
        }
        return result;
    }


}
