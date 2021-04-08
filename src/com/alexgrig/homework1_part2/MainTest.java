package com.alexgrig.homework1_part2;

import java.util.Arrays;
/*
* Задача.
* Написать сортировку пузырьком или бинарный поиск элемента в массиве.
 * */
public class MainTest {
    public static void main(String[] args) {

        int[] arr = {1, 3, 23, 0, 5, 6, 7, 9, 13, 89, 101};
        int target = 23;
        System.out.println();
        //сортируем массив
        BubbleSort.sort(arr);

        System.out.println(Arrays.toString(arr));
        int indexOfTarget = BinarySearchClass.binarySearch(arr, target);

        System.out.printf("Позиция числа %d в отсортированном массиве: %d\n", target, indexOfTarget);

        TemperatureConverter tc = new TemperatureConverter(10);
        System.out.println(tc.getFahrenheit());
        System.out.println(tc.getKelvin());
    }
}
