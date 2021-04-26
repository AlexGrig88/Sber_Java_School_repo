package com.alexgrig.homework_exceptions.task2;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class WebPageReader {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String url = null;
        while (true) {
            try {
                System.out.println("\nВведите адрес(URL) сайта, содержимое которого надо получить: ");
                url = scanner.nextLine();
                readContent(url);
                break;
            } catch (MalformedURLException e) {
                System.out.println("Введённый URL неправильного формата. Повторите ввод.");
            } catch (IOException e) {
                System.out.println("Сайта по данному адресу не найдено или отсутствует доступ.");
                System.out.println("Проверьте, не ошиблись ли вы в написании имени сайта и повторите попытку.");
            }
        }
    }

    public static void readContent(String url) throws IOException {

        URL webpage = null;
        URLConnection connect = null;
        try {
            webpage = new URL(url);
            connect = webpage.openConnection();
        } catch (MalformedURLException ex) {
            throw ex;
        } catch (IOException e) {
            throw new IOException();
        }

        try (InputStreamReader in = new InputStreamReader(connect.getInputStream(), "UTF8");
             BufferedReader reader = new BufferedReader(in))
        {
            String line = "";
            while (true) {
                line = reader.readLine();
                if (line != null) {
                    System.out.println(line);
                }
                else {
                    break;
                }
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
