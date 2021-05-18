package com.alexgrig;

import java.io.*;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class MainProgram {

    public static void main(String[] args) {
        File file = new File("resource\\numbers_for_factorial.txt");

        try (SynchFileReader synchReader = new SynchFileReader(file)) {
            int quantityThreads = 10;  //
            long start = System.currentTimeMillis();

            Thread[] threads = new Thread[quantityThreads];

            for (int i = 0; i < quantityThreads; i++) {
                threads[i] = new Thread(() -> {
                    try {
                        while (true) {
                            int number = synchReader.readNumber();
                            System.out.printf("%d! = %d \t\t посчитан потоком #%s\n", number, factorial(number), Thread.currentThread().getName());
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage() + Thread.currentThread().getName() + " завершён");
                    }
                });
                threads[i].start();
            }

            for (int i = 0; i < quantityThreads; i++) {
                threads[i].join();
            }
            System.out.println("Время работы програмы: " + (System.currentTimeMillis() - start) + " мс.");

            //замер времени для последовательной работы программы
//            long start = System.currentTimeMillis();
//            Scanner scanner = new Scanner(new FileInputStream(file));
//            for (int i = 0; i < 18; i++) {
//                System.out.println(factorial(scanner.nextInt()));
//            }
//            System.out.println("Время работы програмы: " + (System.currentTimeMillis() - start) + " мс.");


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Программа завершена!!!");

    }

    public static BigInteger factorial(int num) {
        longCalculation(num);

        BigInteger fact = BigInteger.valueOf(1L);
        for (int i = num; i > 0; i--) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }

    public static void longCalculation(int num) {
        int[] arr = new int[14_000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000);
        }
        //bubble sort
        for (int i = arr.length; i > 0; i--) {
            for (int j = 1; j < i; j++) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
