package com.alexgrig.homework_exceptions.task1.serverside;


import com.alexgrig.homework_exceptions.task1.externalterminal.MyTimerTask;

public class AccountIsLockedException extends Exception {

    private String additionalMessage = "";

    public void setAdditionalMessage(String additionalMessage) {
        this.additionalMessage = additionalMessage;
    }

    public String getAdditionalMessage() {
        return additionalMessage;
    }

    public AccountIsLockedException(String message) {
        super(message);
    }

}