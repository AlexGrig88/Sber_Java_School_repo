package com.alexgrig.homework_exceptions.task1.externalterminal;

import com.alexgrig.homework_exceptions.task1.Terminal;
import com.alexgrig.homework_exceptions.task1.externalterminal.MyTimerTask;
import com.alexgrig.homework_exceptions.task1.externalterminal.TerminalException;
import com.alexgrig.homework_exceptions.task1.serverside.AccountIsLockedException;
import com.alexgrig.homework_exceptions.task1.serverside.ServerTransactException;
import com.alexgrig.homework_exceptions.task1.serverside.TerminalServer;

import java.util.Timer;

public class TerminalImpl implements Terminal {

    //сумма денег, доступная для выдачи терминалом;
    private int availableMoneyTerminal = 1500000;
    //максимальное колличество банкнот, которое может поместиться в корзину приёма терминала
    private final int maxBanknotes = 3000;
    //текущее состояние количества банкнот в терминале
    private int currentBanknotes = 500;

    private String cardNumber;
    private String messageForClient;
    private TerminalServer server;
    private int attempt;

    public int getAttempt() {
        return attempt;
    }

    public void decAttempt() {
        --attempt;
    }

    //создаётся общедоступный терминал, который работает с предоставленным сервером
    public TerminalImpl(String cardNumber) throws TerminalException {
        try {
            this.cardNumber = cardNumber;
            this.server = getBankServer(cardNumber);
            //this.messageForClient = "Введите пожалуйста pin код:";
            attempt = server.getAttempts();
        } catch (TerminalException e) {
            throw new TerminalException(e.getMessage());
        }

    }

    public boolean isValidPin(String pin) throws AccountIsLockedException {
        String ownerPin = null;
        if (attempt == 0) resetAttempts();
        try {
            ownerPin = server.getPin();
            return pin.equals(ownerPin);

        } catch (AccountIsLockedException e) {
            if (server.getTimeRemaining() != 0) { //если таймер уже запущен добавляем информацию об оставшемся периоде
                e.setAdditionalMessage("До снятия блокировки осталось " + server.getTimeRemaining() + " сек.");
            }
            throw e;  //пробрасывем этот же объект исключения дальше
        }
    }

    private void resetAttempts() {
        attempt = server.getAttempts();
    }


    //по номеру BIN карты определяем сервер банка с которым
    //устанавливаем соединение
    private TerminalServer getBankServer(String cardNumber) throws TerminalException {
        String bin = cardNumber.substring(0, 6);
        switch (bin) {
            case "400000":
                return new TerminalServer(cardNumber);
            default:
                throw new TerminalException("Ваш банк нашим терминалом не обслуживается. До свидания!");
        }
    }

    public String getMessageForClient() {
        return messageForClient;
    }

    @Override
    public double getBalance() {
        return server.getBalance();
    }

    @Override
    public void depositMoney(int money) throws TerminalException {
        if (countBanknotes(money) > maxBanknotes) {
            throw new TerminalException("Банкомат на данный момент не может принять такую сумму.");
        }
        currentBanknotes = countBanknotes(money);
        server.depositMoney(money);
    }

    @Override
    public void withdrawMoney(int money) throws ServerTransactException, TerminalException {
        //алгоритм выдачи выбора номинала не учитываем, просто считаем счетчик по единой банкноте
        //и сравниваем с доступным к выдачи колличеством банкнот
        try {
            if (money > availableMoneyTerminal) {
                throw new TerminalException("Банкомат на данный момент не может выдать данную сумму.");
            }
            availableMoneyTerminal -= money;
            server.withdrawMoney(money);
        } catch (ServerTransactException e) {
            messageForClient = e.getMessage();
            throw e;
        }
    }

    //функция которая считает банкноты внесённые клиентом
    private int countBanknotes(int money) {
        return currentBanknotes + money / 100;
    }


}