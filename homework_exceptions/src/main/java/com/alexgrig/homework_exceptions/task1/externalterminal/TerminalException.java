package com.alexgrig.homework_exceptions.task1.externalterminal;

import java.io.IOException;

public class TerminalException extends Exception {
    public TerminalException(String message) {
        super(message);
    }
    // метод который даёт сигнал о возврате карты
    // и возможно выполняет ещё какие-то служебные функции
    public void returnCardToTheOwner() {
        System.out.println("Вжик! - Заберите карту.");
    }
}
