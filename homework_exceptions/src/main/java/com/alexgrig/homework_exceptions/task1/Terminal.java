package com.alexgrig.homework_exceptions.task1;

import com.alexgrig.homework_exceptions.task1.externalterminal.TerminalException;
import com.alexgrig.homework_exceptions.task1.serverside.ServerTransactException;

public interface Terminal {
    double getBalance();
    void depositMoney(int money) throws TerminalException;
    void withdrawMoney(int money) throws ServerTransactException, TerminalException;
}
