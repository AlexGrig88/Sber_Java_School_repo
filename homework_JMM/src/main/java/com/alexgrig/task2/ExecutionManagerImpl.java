package com.alexgrig.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutionManagerImpl implements ExecutionManager {

    private final ExecutorService service;
    private volatile Context context;

    public ExecutionManagerImpl() {
        this.service = Executors.newFixedThreadPool(4);
    }

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {

        //Future[] futures = new Future[tasks.length];
        List<Future> futureList = Collections.synchronizedList(new ArrayList<>());
        for (Runnable task : tasks) {
            futureList.add(service.submit(task));
        }

        new Thread(() -> {
            for (Future future : futureList) {  //вызывая метод get засталяем поток подождать пока
                // все таски завершатся
                try {
                    future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            callback.run();
            service.shutdown();
        }).start();


        System.out.println("Execute return");

        Context context = new Context() {
            @Override
            public int getCompletedTaskCount() {
                int counter = 0;
                for (Future future : futureList) {
                    if (future.isDone()) ++counter;
                }
                return counter;
            }

            @Override
            public int getFailedTaskCount() {
                return 0;
            }

            @Override
            public int getInterruptedTaskCount() {
                return 0;
            }

            @Override
            public void interrupt() {
                for (Runnable task : tasks) {
                }
            }

            @Override
            public boolean isFinished() {
                boolean isFinished = true;
                for (Future future : futureList) {
                    isFinished = (future.isDone() || future.isCancelled()) && isFinished;
                }
                return isFinished;
            }
        };

        return context;

    }
}

