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

    private long blockingTime = 10000L;
    private String cardNumber;
    private int attempt = 3;
    private String messageForClient;
    private TerminalServer server;

    public long getBlockingTime() {
        return blockingTime;
    }

    public void decAttempt() {
        attempt--;
    }

    public int getAttempt() {
        return attempt;
    }

    //создаётся общедоступный терминал, который работает с предоставленным сервером
    //и использует предоставленную реализацию алгоритма проверки пина
    public TerminalImpl(String cardNumber) throws TerminalException {
        try {
            this.cardNumber = cardNumber;
            this.server = getBankServer(cardNumber);
            this.messageForClient = "Введите пожалуйста pin код:";
        } catch (TerminalException e) {
            throw new TerminalException(e.getMessage());
        }

    }

    public synchronized boolean isValidPin(String pin) throws AccountIsLockedException {
        String ownerPin = null;
        try {
            ownerPin = server.getPin(attempt);
        } catch (AccountIsLockedException e) {
            //запускаем таймер, который по истечении 10 сек, вернёт исходные 3 попытки
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Timer timer = new Timer();
                    MyTimerTask task = new MyTimerTask();
                    timer.schedule(task, 1000L, 1000L);
                    try {
                        Thread.sleep(blockingTime);
                        timer.cancel();
                        System.out.println("Блокировка снята!\nВведите пожалуйста pin код:");
                        attempt = 3;
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            });
            thread.start();
            throw new AccountIsLockedException(e.getMessage());
        }
        return pin.equals(ownerPin);
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