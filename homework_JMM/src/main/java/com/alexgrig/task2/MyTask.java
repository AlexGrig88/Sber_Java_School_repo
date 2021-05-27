package com.alexgrig.task2;

public class MyTask implements Runnable {

    private final long timeRunning;


    public MyTask(long timeRunning) {
        this.timeRunning = timeRunning;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timeRunning);
        } catch (InterruptedException e) {

        }
    }
}