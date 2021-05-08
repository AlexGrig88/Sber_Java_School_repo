package com.alexgrig.task2;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClassEncryption {
    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\UserGrig\\testJavaFolder";
        String classname = "TargetClass";
        String key = "4";

        System.out.println(key);
        encrypt(path, classname, key);
    }

    public static void encrypt(String path, String classname, String key) throws Exception {
        Path file = Paths.get(path + "\\" + classname + ".class");
        byte[] content = Files.readAllBytes(file);
        byte[] encryptedContent = new byte[content.length];
        //прибавляем к каждому байту значение ключа
        int keyInt = Integer.parseInt(key);
        for (int i = 0; i < encryptedContent.length; i++) {
            encryptedContent[i] = (byte)(content[i] + keyInt);
        }
        writeToFile(path, classname, encryptedContent);
    }

    public static void writeToFile(String path, String filename, byte[] content) throws Exception {
        File file = new File(path + "\\encrypted\\" + filename + ".class");
        FileOutputStream out = new FileOutputStream(file);
        out.write(content);
        out.close();
    }
}
