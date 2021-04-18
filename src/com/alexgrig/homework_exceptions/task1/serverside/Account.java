package com.alexgrig.homework_exceptions.task1.serverside;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Account {
    private Person owner;
    private int balance;
    private String cardNumber;
    private String pinCode;
    private static Set<String> uniqueCardNumbers = new HashSet<>();

    public Account(Person owner, int balance, String pinCode, String cardNumber) {
        this.owner = owner;
        this.balance = balance;
        this.pinCode = pinCode;
        this.cardNumber = cardNumber;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    private String generateUniqueCardNumber() {
        Random random = new Random();
        //Bank Identification Number (BIN)
        String bin = "400000";
        //Customer account number
        String customerAccNum = "";
        for (int i = 0; i < 9; i++) {
            customerAccNum += Integer.toString(random.nextInt(10));
        }
        //checksum
        String checkSum = Integer.toString(random.nextInt(10));
        String targetNumber = bin + customerAccNum + checkSum;

        int sizeBefore = uniqueCardNumbers.size();
        uniqueCardNumbers.add(targetNumber);
        int sizeAfter = uniqueCardNumbers.size();
        //если такой номер уже существует генерим новый
        while (sizeAfter == sizeBefore) {
            customerAccNum = "";
            for (int i = 0; i < 9; i++) {
                customerAccNum += Integer.toString(random.nextInt(10));
            }
            checkSum = Integer.toString(random.nextInt(10));
            targetNumber = bin + customerAccNum + checkSum;
            uniqueCardNumbers.add(targetNumber);
            sizeAfter = uniqueCardNumbers.size();
        }
        return targetNumber;
    }
}
