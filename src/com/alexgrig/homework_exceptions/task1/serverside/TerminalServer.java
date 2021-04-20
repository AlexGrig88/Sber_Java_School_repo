package com.alexgrig.homework_exceptions.task1.serverside;

import com.alexgrig.homework_exceptions.task1.Terminal;
import com.alexgrig.homework_exceptions.task1.externalterminal.MyTimerTask;
import com.alexgrig.homework_exceptions.task1.serverside.Account;
import com.alexgrig.homework_exceptions.task1.serverside.AccountIsLockedException;
import com.alexgrig.homework_exceptions.task1.serverside.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class TerminalServer implements Terminal {

    private int attempts = 3;
    private final int blockingTime = 10;
    private MyTimerTask timerTask;
    private Account userAccount;
    private final List<Account> accountsList = new ArrayList<>();
    private AccountIsLockedException lockedException;

    {
        accountsList.add(new Account(new Person("Petr"), 2350000, "1234", "4000007433331234"));
        accountsList.add(new Account(new Person("Dmitriy"), 35000, "1111", "4000007433331235"));
        accountsList.add(new Account(new Person("Ivan"), 55000, "2222", "4000007433331236"));
    }

    public int getAttempts() {
        return attempts;
    }

    // даём доступ к времени, оставшемуся до конца сброса таймера
    public int getTimeRemaining() {
        if (timerTask != null) {
            return timerTask.getCounter();
        } else {
            return 0;  //если таймер ещё не запускался
        }
    }

    public TerminalServer(String cardNumber) {
        userAccount = accountsList.stream().filter(n -> n.getCardNumber() == cardNumber).findFirst().get();
    }

    public String getPin() throws AccountIsLockedException {
        if (attempts > 0) {               // пока попытки не истекли даём доступ к пин коду аккаунта
            attempts--;
            return userAccount.getPinCode();
        } else if (lockedException == null) { // когда попытки закончились
            runBlockingTimer(blockingTime);   //запускаем таймер блокировки, создаём и бросаем исключение
            String message = "Аккаунт заблокирован на " + blockingTime + " секунд.";
            lockedException = new AccountIsLockedException(message);
            throw lockedException;
        } else {                            // при повторном обращении, если время блокировки не истекло
            throw lockedException;          // выбрасываем тот же объект исключения
        }

    }

    private void runBlockingTimer(int blockingTime) {
        new Thread(() -> {
            Timer timer = new Timer("Timer",true);
            timerTask = new MyTimerTask(blockingTime);
            timer.schedule(timerTask, 1000L, 1000L);
            try {
                Thread.sleep(1000 * blockingTime + 1000);
                timer.cancel();
                attempts = 3;             // после истечения времени блокировки восстанавливаем попытки
                lockedException = null;   // и уничтожаем неактуальный объект исключения
                System.out.println("\nБлокировка снята!\nВведите пожалуйста pin код:");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }).start();
    }

    @Override
    public double getBalance() {
        return userAccount.getBalance();
    }

    @Override
    public void depositMoney(int money) {
        int newBalance = userAccount.getBalance() + money;
        userAccount.setBalance(newBalance);
    }

    @Override
    public void withdrawMoney(int money) throws ServerTransactException {
        if (money > userAccount.getBalance()) {
            throw new ServerTransactException("На счете недостаточно денег. Для выдачи доступно " + userAccount.getBalance() + " руб");
        } else {
            userAccount.setBalance(userAccount.getBalance() - money);
        }
    }
}
