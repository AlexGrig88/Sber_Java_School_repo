package com.alexgrig;

import java.io.*;
import java.util.Scanner;

public class SynchFileReader implements Closeable {

    Object monitor = new Object();

    private FileInputStream fin;
    private Scanner scan;

    public SynchFileReader(File file) {

        try {
            fin = new FileInputStream(file);
            scan = new Scanner(fin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int readNumber() throws Exception {
        synchronized (monitor) {
            if(scan.hasNext()) {
                return scan.nextInt();
            } else {
                throw new Exception("Данные прочитаны.");
            }
        }
    }

    @Override
    public void close() throws IOException {
        System.out.println("\nПоток закрыт");
        fin.close();
        scan.close();
    }
}
