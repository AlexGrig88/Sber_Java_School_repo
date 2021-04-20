package com.alexgrig.homework_exceptions.task1.externalterminal;

import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
    private int counter = 10;

    public MyTimerTask(int counter) {
        this.counter = counter;
    }

    public MyTimerTask() {
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public void run() {
        counter--;
        //System.out.println("До снятия блокировки осталось " + counter);
    }
}
