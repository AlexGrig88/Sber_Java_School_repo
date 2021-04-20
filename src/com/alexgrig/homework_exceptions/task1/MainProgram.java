package com.alexgrig.homework_exceptions.task1;

import com.alexgrig.homework_exceptions.task1.externalterminal.TerminalException;
import com.alexgrig.homework_exceptions.task1.externalterminal.TerminalImpl;
import com.alexgrig.homework_exceptions.task1.serverside.AccountIsLockedException;
import com.alexgrig.homework_exceptions.task1.serverside.ServerTransactException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainProgram {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Добрый день, вставьте, пожалуйста, вашу карту.");
        //Считывается номер карты и создаётся сеанс взаимодействия клиента
        //со своим счетом
        String cardNumber = "4000007433331234";

        // для данной карты pin = 1234

        TerminalImpl terminal = null;
        try {
            terminal = new TerminalImpl(cardNumber);
        } catch (TerminalException e) {
            System.out.println(e.getMessage());
            e.returnCardToTheOwner();
            return;
        }
        System.out.println("Введите пожалуйста pin код: ");
        String pinCode = getUserInput();
        while (true) {
            try {
                if (terminal.isValidPin(pinCode)) break;
            } catch (AccountIsLockedException e) { // сообщаем сколько времени до снятия блокировки, внутри терминала
                System.out.println(e.getMessage()); // запускается таймер
                pinCode = getUserInput();  //не блокируем пользовательский ввод
                continue;
            } catch (TerminalException ex) {  //при потпытки снова отправить пин, получаем сообщение об оставшемся времени
                System.out.println(ex.getMessage());
                pinCode = getUserInput();  //не блокируем пользовательский ввод
                continue;
            }
            terminal.decAttempt();
            System.out.println("Вы ввели неверный пин.");
            if (terminal.getAttempt() == 0) continue;
            System.out.println("У вас осталось " + (terminal.getAttempt()) + " попытки");

            pinCode = getUserInput();
        }

        System.out.println("Добро пожаловать!");
        boolean isExit = false;
        while (true && !isExit) {
            System.out.println("\nВыбирите операцию, которую вы хотите произвести(1, 2, 3, 0): ");
            System.out.println("1. Проверить состояние счета");
            System.out.println("2. Положить деньги");
            System.out.println("3. Снять деньги");
            System.out.println("0. Выход");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    System.out.println("\nНа вашем счету: " + terminal.getBalance() + " руб.");
                    break;
                case "2":
                    System.out.println("Внесите необходимую сумму(кратную 100): ");
                    try {
                        int money = scanner.nextInt();
                        if (money % 100 != 0) {
                            System.out.println("Сумма не кратна 100. Попробуйте ещё раз.");
                            break;
                        }
                        terminal.depositMoney(money);
                        System.out.println("Ваш счет пополнен.");
                    } catch (TerminalException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Попробуйте внести меньшую сумму.");
                    } catch (InputMismatchException e) {
                        System.out.println("Неккоректный ввод! Пожалуйста используйте цифры.");
                        scanner.nextLine();
                    }
                    break;
                case "3":
                    System.out.println("Какую сумму вы хотите снять(кратную 100): ");
                    try {
                        int money = scanner.nextInt();
                        if (money % 100 != 0) {
                            System.out.println("Сумма не кратна 100. Попробуйте ещё раз.");
                            break;
                        }
                        terminal.withdrawMoney(money);
                        System.out.println("Заберите деньги.");
                    } catch (ServerTransactException e) {
                        System.out.println("Отказ!");
                        System.out.println(terminal.getMessageForClient());
                    } catch (TerminalException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Попробуйте запросить меньшую сумму.");
                    } catch (InputMismatchException e) {
                        System.out.println("Неккоректный ввод! Пожалуйста используйте цифры.");
                        scanner.nextLine();
                    }
                    break;
                case "0":
                    System.out.println("\nДо свидания!");
                    isExit = true;
                    break;
                default:
                    System.out.println("Неккоректный ввод! Пожалуйста повторите.");
                    break;
            }
        }
    }

    public static String getUserInput() {
        scanner = new Scanner(System.in);
        int pin1 = 22, pin2 = 22, pin3 = 22, pin4 = 22;
        while (true) {
            try {
                pin1 = pin1 == 22 ? scanner.nextInt() : pin1;
                pin2 = pin2 == 22 ? scanner.nextInt() : pin2;
                pin3 = pin3 == 22 ? scanner.nextInt() : pin3;
                pin4 = pin4 == 22 ? scanner.nextInt() : pin4;
                break;
            } catch (InputMismatchException e) {
                System.out.println("Пин код должен содержать только цифры. Повторите попытку!");
                scanner.next();
            }
        }
        return "" + pin1 + pin2 + pin3 + pin4;
    }

}
