package com.alexgrig.homework1.sgu;

import java.util.Scanner;

//2039. Строки. Количество слов в тексте
public class WordsCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int counterWords = 0;
        boolean lock = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                if (!lock) {
                    counterWords++;
                }
                lock = true;
            } else {
                lock = false;
            }
        }
        System.out.println(counterWords);
    }
}
