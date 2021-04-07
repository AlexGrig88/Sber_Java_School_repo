package com.alexgrig.homework1.sgu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//2028. Числа 0-4
public class CounterNumInArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        int[] count = new int[5];
        for (int i = 0; i < size; i++) {
            switch (arr[i]) {
                case 0:
                    count[0]++;
                    break;
                case 1:
                    count[1]++;
                    break;
                case 2:
                    count[2]++;
                    break;
                case 3:
                    count[3]++;
                    break;
                case 4:
                    count[4]++;
                    break;
            }
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                System.out.println(i + " " + count[i]);
            }
        }
    }





    private static void extracted() {
        Scanner scanner = new Scanner(System.in);
        int n1 = scanner.nextInt();
        int[] a = new int[n1];
        for (int i = 0; i < n1; i++) {
            a[i] = scanner.nextInt();
        }

        int n2 = scanner.nextInt();
        int[] b = new int[n2];
        for (int i = 0; i < n2; i++) {
            b[i] = scanner.nextInt();
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if (a[i] == b[j]) {
                    list.add(a[i]);
                    b[j] = 0;
                    break;
                }
            }
        }

        System.out.println(list.size());
        if (list.size() > 0) {
            for (int x : list) {
                System.out.print(x + " ");
            }
        } else {
            System.out.print("");
        }
    }
}
