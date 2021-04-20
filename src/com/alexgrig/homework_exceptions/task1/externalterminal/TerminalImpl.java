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

    private MyTimerTask timerTask;
    private int timeRemaining = 0;
    private int attempt = 3;


    public long getTimeRemaining() {
        return timeRemaining;
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
            //this.messageForClient = "Введите пожалуйста pin код:";
        } catch (TerminalException e) {
            throw new TerminalException(e.getMessage());
        }

    }

    public boolean isValidPin(String pin) throws AccountIsLockedException, TerminalException {
        String ownerPin = null;
        //проверяем запустился ли таймер обратного отсчёта
        if (timerTask != null)  timeRemaining = timerTask.getCounter();
        try {
            if (timeRemaining == 0) {
                ownerPin = server.getPin(attempt);
                return pin.equals(ownerPin);
            } else {
                //сюда мы попадаем, когда уже возник AccountIsLockedException, но клиент продолжает набирать
                //пин код. Тогда мы ему кидаем ислючение TerminalException с оставшимся временем до конца снятия блока
                throw new TerminalException("До снятия блокировки осталось " + timeRemaining + " сек.");
            }
        } catch (AccountIsLockedException e) {
            //из исключения извлекаем время, но которое заблокирован аккаунт
            //и запускаем таймер, по истечении которого восстановятся попытки
            handleAccountIsLockedException(e.getBlockingTime());
            timeRemaining = e.getBlockingTime();
            attempt = 3;
            throw e;
        }
    }
    //метод обратный отсчет таймера для разблокировки аккаунта
    private void handleAccountIsLockedException(int blockingTime) {
        new Thread(() -> {
            Timer timer = new Timer("Timer",true);
            timerTask = new MyTimerTask(blockingTime);
            timer.schedule(timerTask, 1000L, 1000L);
            try {
                Thread.sleep(1000 * blockingTime + 1000);
                timer.cancel();
                System.out.println("Блокировка снята!\nВведите пожалуйста pin код:");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }).start();
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