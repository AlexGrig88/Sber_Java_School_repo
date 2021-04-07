package com.alexgrig.homework1.sgu;

import java.util.Scanner;

/*
    Задание1.
    Зарегистрироваться на сайте http://acm.sgu.ru/lang/register2.php.
    Решить как минимум по одной задаче из разделов (лучше больше):
    - Ветвления, циклы
    - Массивы
    - Строки

 */
public class Solution {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //task2003();
        //task2004();
        //task2005();
        //task2014_AlgorithmEvklida();
        //analysisOfAge();
        //createPolindrom();
        //getQuantityMinimums();
        //queryMinInSubArray();
        getNotStrangeWords();

    }
    //номер таски соответствует ID задачи с сайта http://acm.sgu.ru/lang/register2.php.

    //2003. Альтернированная сумма чисел
    public static void task2003() {
        int n = scanner.nextInt();

        int alternatedSum = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                alternatedSum += scanner.nextInt();
            } else {
                alternatedSum -= scanner.nextInt();
            }
        }
        System.out.println("Альтернированная сумма = " + alternatedSum);
    }

    //2004. Високосный год
    //Задан номер года y. Ваша задача вывести 1, если год високосный. Выведите 0 в противном случае.
    public static void task2004() {
        int year = scanner.nextInt();
        int result = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) ? 1 : 0;
        System.out.println(result);
    }

    //2005. Индекс первого минимума
    //Заданы n целых чисел. Выведите индекс (позицию) первого из минимальных элементов последовательности.
    // Элементы последовательности занумерованы от 1 слева направо.
    public static void task2005() {
        int n = scanner.nextInt();
        int indexOfMinNumber = 0;
        int min = scanner.nextInt();
        for (int i = 1; i < n; i++) {
            int current = scanner.nextInt();
            if (current < min) {
                min = current;
                indexOfMinNumber = i + 1;  //прибавляем 1 т.к. индексы по условию начинаются с 1
            }
        }
        System.out.println(indexOfMinNumber);

    }

    //2014. Алгоритм Евклида
    public static void task2014_AlgorithmEvklida() {
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        int count = 0;
        while (num1 > 0 && num2 > 0) {
            if (num1 > num2) {
                num1 -= num2;
                count++;
            } else {
                num2 -= num1;
                count++;
            }
        }
        System.out.println(count);
        System.out.println(num1 > num2 ? num1 : num2);

    }

    //2011. Анализ возраста
    public static void analysisOfAge() {
        Scanner scanner = new Scanner(System.in);
        int age = scanner.nextInt();
        if (age < 7) {
            System.out.println("preschool child");
        } else if (age < 18) {
            System.out.println("schoolchild " + (age - 6));
        } else if (age < 23) {
            System.out.println("student " + (age - 17));
        } else {
            System.out.println("specialist");
        }

    }
    //2024. Сделать палиндром!
    public static void createPolindrom() {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        short count = 0;
        int k = 0;
        for (int i = size - 1; i >= size / 2; i--) {
            if (arr[i] != arr[k]) {
                count++;
                k++;
            } else {
                k++;
            }
        }
        System.out.println(count);
    }

    //2013. Количество минимумов
    public static void getQuantityMinimums() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int min = Integer.MAX_VALUE;
        int counter = 0;
        for (int i = 0; i < n; i++) {
            int current = scanner.nextInt();
            if (current < min) {
                min = current;
                counter = 1;
            } else if (current == min) {
                counter++;
            } else {

            }
        }

        System.out.println(counter);

    }

    ///////////////////////////////////////////////////
    //2025. Запросы минимума на подмассиве
    public static void queryMinInSubArray() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int counterQuery = scanner.nextInt();
        int[][] pairs = new int[counterQuery][2];
        for (int i = 0; i < counterQuery; i++) {
            for (int j = 0; j < 2; j++) {
                pairs[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < counterQuery; i++) {
            System.out.println(minInSubArray(arr, pairs[i][0], pairs[i][1]));
        }
    }

    public static int minInSubArray(int[] arr, int start, int finish) {
        int minValue = arr[start - 1];
        for (int i = start; i < finish; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            }
        }
        return minValue;
    }
    ///////////////////////////////////////////////////////////

    //2036. Строки. Странные слова
    public static void getNotStrangeWords() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = scanner.next();
        }
        for (String s : words) {
            if (!isStrangeWord(s)) {
                System.out.println(s);
            }
        }
    }

    private static boolean isStrangeWord(String str) {
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            if(isVowel(str.charAt(i))) {
                counter++;
                if (counter >= 3) {
                    return true;
                }
            } else {
                counter = 0;
            }
        }
        return false;
    }

    public static boolean isVowel(char ch) {
        boolean res = false;
        char[] arr = {'a', 'o', 'e', 'y', 'u', 'i'};
        for (int i = 0; i < arr.length; i++) {
            if (ch == arr[i]) {
                res = true;
                break;
            }
        }
        return res;
    }

    //////////////////////////////////////////////////////
}
