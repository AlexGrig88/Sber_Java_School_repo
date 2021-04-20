package com.alexgrig.homework_exceptions.task1.serverside;


import com.alexgrig.homework_exceptions.task1.externalterminal.MyTimerTask;

import java.util.Timer;

public class AccountIsLockedException extends Exception {

    private int blockingTime;

    public int getBlockingTime() {
        return blockingTime;
    }


    public AccountIsLockedException(String message, int blockingTime) {
        super(message);
        this.blockingTime = blockingTime;
    }

}