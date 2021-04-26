package com.alexgrig.homework1.sgu;

import java.util.Scanner;

//2038. Строки. Самое длинное слово
public class TheLongestWord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int max = 0, count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                count++;
            } else {
                if (count > max) max = count;
                count = 0;
            }
        }
        System.out.println(max);
    }
}
