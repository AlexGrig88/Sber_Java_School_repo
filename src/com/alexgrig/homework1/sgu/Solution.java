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
        //task2001();
        //task2002();
        //task2003();
        //task2004();
        //task2005();
        //task2006();
        //task2007();
        //task2008();
        //task2009();
        //task2012();
        //task2015();
        //task2014_AlgorithmEvklida();
        //analysisOfAge();
        //createPolindrom();
        //getQuantityMinimums();
        //queryMinInSubArray();
        getNotStrangeWords();

    }
    //номер таски соответствует ID задачи с сайта http://acm.sgu.ru/lang/register2.php.

    public static void task2001() {
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(a + b);
    }

    public static void task2002() {
        int n = scanner.nextInt();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += scanner.nextInt();
        }

        System.out.println(sum);
    }


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

    private static void task2015() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = 2;
        System.out.println(x++);
        boolean flag = false;
        while (x <= n) {
            flag = false;
            for (int i = 2; i < x; i++) {
                if (x % i == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println(x);
            }
            x++;
        }
    }

    private static void task2012() {
        Scanner scanner = new Scanner(System.in);
        int a1 = scanner.nextInt();
        int b1 = scanner.nextInt();
        int a2 = scanner.nextInt();
        int b2 = scanner.nextInt();
        if (a1 == a2 && b1 + b2 == a1) {
            System.out.println("YES");
        } else if (b1 == b2 && a1 + a2 == b1) {
            System.out.println("YES");
        } else if (a1 == b2 && a2 + b1 == a1) {
            System.out.println("YES");
        } else if (a2 == b1 && a1 + b2 == a2) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static void task2009() {
        Scanner scanner = new Scanner(System.in);
        int quantity = scanner.nextInt();
        int sum = scanner.nextInt();
        int k = 2;
        for (int i = 2; i < quantity + 1; i++) {
            if (i == k) {
                sum += scanner.nextInt();
                k *= 2;
                continue;
            }
            scanner.next();
        }
        System.out.println(sum);
    }

    private static void task2008() {
        Scanner scanner = new Scanner(System.in);
        int quantity = scanner.nextInt();
        int capacity = scanner.nextInt();

        int counter = 0;
        int currentCapacity = 0;
        for (int i = 0; i < quantity; i++) {
            int weight = scanner.nextInt();
            if (currentCapacity + weight < capacity) {
                currentCapacity += weight;
                counter++;
            } else if(currentCapacity + weight == capacity) {
                currentCapacity += weight;
                counter++;
                break;
            } else {

            }
        }
        System.out.println(counter + " " + currentCapacity);
    }

    private static void task2007() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int divider = 2;
        int counter = 0;
        while (num % divider == 0) {
            counter++;
            divider *= 2;
        }
        System.out.println(counter);
    }

    private static void task2006() {
        Scanner scanner = new Scanner(System.in);
        int cm = scanner.nextInt();
        int inch = cm / 3;
        if (cm % 3 == 2) {
            inch += 1;
        }
        int fount = inch / 12;
        inch = inch % 12;
        System.out.print(fount + " " + inch);
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
