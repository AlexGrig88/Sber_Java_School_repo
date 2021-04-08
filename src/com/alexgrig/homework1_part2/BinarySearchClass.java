package com.alexgrig.homework1_part2;

import java.util.Arrays;

public class BinarySearchClass {
    //метод возвращает -1 если число не найдено, иначе возвращается его индекс
    //входной массив должен быть отсортирован
    public static int binarySearch(int[] sortedArr, int number) {
        int left = 0, right = sortedArr.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (number > sortedArr[mid]) {
                left = mid + 1;
            } else if (number < sortedArr[mid]) {
                right = mid - 1;
            } else {
                index = mid;
                break;
            }
        }
        return index;
    }
}
