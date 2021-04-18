package com.alexgrig.homework_exceptions.task1.serverside;


public class AccountIsLockedException extends Exception {

    public AccountIsLockedException(String message) {
        super(message);
    }
}