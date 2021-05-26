package com.alexgrig.task2;

public class MyTask implements Runnable {

    private final long timeRunning;
    private volatile boolean isStarted = false;

    public boolean isStarted() {
        return isStarted;
    }

    public MyTask(long timeRunning) {
        this.timeRunning = timeRunning;
    }

    @Override
    public void run() {
        isStarted = true;
        try {
            Thread.sleep(timeRunning);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}