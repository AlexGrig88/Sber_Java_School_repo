package com.alexgrig.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class EncryptedClassLoader extends ClassLoader {
    private final String key;
    private final File dir;

    public EncryptedClassLoader(String key, File dir, ClassLoader parent) {
        super(parent);
        this.key = key;
        this.dir = dir;
    }

    @Override
    public Class<?> findClass(String name) {
        byte[] b = new byte[0];
        try {
            b = loadClassData(dir, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(File pathFile, String name) throws Exception {

        File file = new File(pathFile.toString() + "\\" + name + ".class");
        if (file.length() > Integer.MAX_VALUE) {
            throw new Exception("File is too large!");
        }

        int lengthFile = (int) file.length();
        byte[] encryptedContent = new byte[lengthFile];

        try (FileInputStream fin = new FileInputStream(file)) {

           fin.read(encryptedContent, 0, lengthFile);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        byte[] decryptedContent = new byte[encryptedContent.length];
        int keyInt = Integer.parseInt(key);
        //по полученному цифровому ключу отнимаем от каждого байта данное значение
        for (int i = 0; i < decryptedContent.length; i++) {
            decryptedContent[i] = (byte) (encryptedContent[i] - keyInt);
        }


        return decryptedContent;
    }
}
