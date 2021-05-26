package com.alexgrig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ScalableThreadPool implements ThreadPool {


    private BlockingQueue taskQueue;
    private List<PoolThreadRunnable> runnables = new ArrayList<>();
    private boolean isStopped;
    private List<PoolThreadRunnable> additional = new ArrayList<>();
    private int counterAdd;


    public ScalableThreadPool(int minNumberOfThreads, int maxNumberOfThreads) {
        counterAdd = maxNumberOfThreads - minNumberOfThreads;

        taskQueue = new LinkedBlockingQueue();

        for (int i = 0; i < minNumberOfThreads; i++) {
            runnables.add(new PoolThreadRunnable(taskQueue));
        }

    }

    @Override
    public void start() {
        for (PoolThreadRunnable runnable : runnables) {
            new Thread(runnable).start();
        }

    }

    @Override
    public void execute(Runnable task) throws Exception {
        if (this.isStopped) throw
                new IllegalStateException("ThreadPool is stopped");

        this.taskQueue.offer(task);


    }

    public synchronized void stop() {
        this.isStopped = true;
        for (PoolThreadRunnable runnable : runnables) {
            runnable.doStop();
        }
        for (PoolThreadRunnable runnable : additional) {
            runnable.doStop();
        }
    }

    public synchronized void waitUntilAllTasksFinished() {
        while (this.taskQueue.size() > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
