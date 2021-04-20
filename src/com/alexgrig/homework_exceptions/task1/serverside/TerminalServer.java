package com.alexgrig.homework_exceptions.task1.serverside;

import com.alexgrig.homework_exceptions.task1.Terminal;
import com.alexgrig.homework_exceptions.task1.serverside.Account;
import com.alexgrig.homework_exceptions.task1.serverside.AccountIsLockedException;
import com.alexgrig.homework_exceptions.task1.serverside.Person;

import java.util.ArrayList;
import java.util.List;

public class TerminalServer implements Terminal {

    private final int blockingTime = 10;
    private Account userAccount;

    private final List<Account> accountsList = new ArrayList<>();

    {
        accountsList.add(new Account(new Person("Petr"), 2350000, "1234", "4000007433331234"));
        accountsList.add(new Account(new Person("Dmitriy"), 35000, "1111", "4000007433331235"));
        accountsList.add(new Account(new Person("Ivan"), 55000, "2222", "4000007433331236"));
    }

    public TerminalServer(String cardNumber) {
        userAccount = accountsList.stream().filter(n -> n.getCardNumber() == cardNumber).findFirst().get();
    }

    public String getPin(int attempt) throws AccountIsLockedException {
        if (attempt > 0) {
            return userAccount.getPinCode();
        } else {
            String message = "Аккаунт заблокирован на " + blockingTime + " секунд.";
            throw new AccountIsLockedException(message, blockingTime);
        }

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
