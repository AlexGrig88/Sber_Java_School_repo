package com.alexgrig;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class TestZip {
    public static void main(String[] args) throws IOException {
        // write your code here
        Person person1 = new Person("Ivanov", 22);

        //запись объекта в zip
        File file = new File("data.zip");
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(file, true));
        ZipEntry ze = new ZipEntry("test.ser");
        zos.putNextEntry(ze);
        ObjectOutputStream oos = new ObjectOutputStream(zos);
        oos.writeObject(person1);
        Person person2 = new Person("Petrov", 33);
        oos.writeObject(person2);
        Person person3 = new Person("Sidorov", 44);
        oos.writeObject(person3);
        //      oos.close();

        //чтение объекта из zip
        File file2 = new File("data.zip");
        ZipFile zf = new ZipFile(file2);
        ZipEntry zeRead = zf.getEntry("test.ser");
        ObjectInputStream ois = new ObjectInputStream(zf.getInputStream(zeRead));

        try {
            while (true) {
                Object obj = ois.readObject();
                Person p = (Person) obj;
                System.out.println(p);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException ex) {
            System.out.println("No object in file!");
        }

        ois.close();
    }

}
