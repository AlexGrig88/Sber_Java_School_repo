package com.alexgrig;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainProgram {
    public static void main(String[] args) throws Exception {

//        FixedThreadPool threadPool = new FixedThreadPool(3);
        ScalableThreadPool threadPool = new ScalableThreadPool(3, 6);

        threadPool.start();

        for(int i=0; i<10; i++) {
//            Thread.sleep(1000);
            int taskNo = i;
            threadPool.execute( () -> {
                String message =
                        Thread.currentThread().getName()
                                + ": Task " + taskNo + " done" ;
                longCalculation(5);
                System.out.println(message);
            });
        }
        int taskNew = 11;
        threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ": Task " + taskNew + " start");
                longCalculation(5);
            System.out.println(Thread.currentThread().getName() + ": Task " + taskNew + " end and done");

        });


        threadPool.waitUntilAllTasksFinished();
        threadPool.stop();

    }


    public static void longCalculation(int num) {
        int[] arr = new int[5000];
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
