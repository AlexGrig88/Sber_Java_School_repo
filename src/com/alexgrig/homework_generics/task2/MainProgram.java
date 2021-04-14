package com.alexgrig.homework_generics.task2;

import java.util.Arrays;
import java.util.List;

public class MainProgram {
    public static void main(String[] args) {

        List<Integer> res = CollectionUtils.<Integer> range(Arrays.asList(4, 8, 1, 2, 7, 3, 5, 6), 3, 6);
        System.out.println(res);

        List<Integer> list1 = Arrays.asList(4, 8, 1, 2, 7, 3, 5, 6);
        List<Integer> list2 = Arrays.asList(1, 8, 7, 3);
        System.out.println(CollectionUtils.<Integer> containsAll(list1, list2));
    }
}

