package com.alexgrig;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainProgram {
    public static void main(String[] args) throws Exception {

        FixedThreadPool threadPool = new FixedThreadPool(3);

        threadPool.start();

//        threadPool.execute(() -> {
//            try {
//                System.out.println("start");
//                Thread.sleep(3000L);
//                System.out.println("end");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

        for(int i=0; i<10; i++) {

            int taskNo = i;
            threadPool.execute( () -> {
                String message =
                        Thread.currentThread().getName()
                                + ": Task " + taskNo ;
                System.out.println(message);
            });
        }

        threadPool.waitUntilAllTasksFinished();
        threadPool.stop();

    }
}
