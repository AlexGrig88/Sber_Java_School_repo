package com.alexgrig.task2;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class MainProgram {

    public static void main(String[] args) throws Exception{
        //место хранения зашифрованного класс файла
        File dir = new File("C:\\Users\\UserGrig\\testJavaFolder\\encrypted");
        String classname = "TargetClass"; //имя зашифрованного файла
        String key = "4";

        EncryptedClassLoader myClassLoader = new EncryptedClassLoader(key, dir, ClassLoader.getSystemClassLoader());
        Class dynamicClass = myClassLoader.findClass(classname);

//       если попытаться загрузить любым другим лоадером, то получим ошибку ClassFormatError
//        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{dir.toURI().toURL()}, ClassLoader.getSystemClassLoader());
//        Class<?> dynamicClass = urlClassLoader.loadClass(classname);


        Object obj = dynamicClass.getConstructor(String.class).newInstance("Java");
        Method m = dynamicClass.getMethod("sayHello");
        m.invoke(obj);
    }
}
