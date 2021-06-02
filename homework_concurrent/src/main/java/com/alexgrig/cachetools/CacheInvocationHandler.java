package com.alexgrig.cachetools;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class CacheInvocationHandler implements InvocationHandler {

    private final Object delegate;
    private final Map<String, Object> cacheMap = new HashMap<>();
    private final String rootDirectory;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;


    public CacheInvocationHandler(Object delegate, String rootDirectory) {
        this.delegate = delegate;
        this.rootDirectory = rootDirectory;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //если метод не помечен аннотацией Cache вызываем оригинальный метод класса
        if (!method.isAnnotationPresent(Cache.class)) {
            return method.invoke(delegate, args);
        }

        String key = key(method, args);
        Cache annotation = method.getAnnotation(Cache.class);

        if (annotation.cacheType() == CacheType.IN_MEMORY) {

            if (cacheMap.containsKey(key)) {
                System.out.println("        Result from cache!");
                return cacheMap.get(key);
            }
            Object invoke = calculateCacheResult(method, annotation, args);
            cacheMap.put(key, invoke);
            return invoke;


        } else if (annotation.cacheType() == CacheType.FILE) {

            String shortFileName = annotation.fileNamePrefix().equals("default") ?
                    method.getName() :
                    annotation.fileNamePrefix();

            if (annotation.zip()) {

                File file = new File(rootDirectory + "\\" + "cache.zip");

                if (!file.exists()) {
                    Object invoke = calculateCacheResult(method, annotation, args);
                    //запись объекта в zip
                    ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(file, true));
                    ZipEntry ze = new ZipEntry(shortFileName + ".ser");
                    zos.putNextEntry(ze);
                    oos = new ObjectOutputStream(zos);
                    SerializationDataUnit unit = new SerializationDataUnit(key, invoke);
                    oos.writeObject(unit);
                    return invoke;

                } else {
                    //чтение объекта из zip
                    if (ois == null) {
                        ZipFile zf = new ZipFile(file);
                        ZipEntry zeRead = zf.getEntry(shortFileName + ".ser");
                        ois = new ObjectInputStream(zf.getInputStream(zeRead));
                    }

                    try {
                        SerializationDataUnit unitCache = null;
                        while (true) {
                            unitCache = (SerializationDataUnit) ois.readObject();
                            if (unitCache.getKey().equals(key)) {
                                System.out.println("        Result from cache file!");
                                return unitCache.getResult();
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (EOFException ex) {      // Если мы дочитали все объекты в файле, но не нашли по ключу
                        System.out.println("*********");
                        Object invoke = calculateCacheResult(method, annotation, args);
                        SerializationDataUnit unit = new SerializationDataUnit(key, invoke);
                        oos.writeObject(unit);
                        return invoke;
                    }

                }

            }

        }
        //Недостижимый результат
        return null;
    }

    private Object calculateCacheResult(Method method, Cache annotation, Object[] args) {
        System.out.println("calculate...");
        Object invoke = null;
        if (method.getReturnType() == java.util.List.class) {
            List<Object> list = null;
            try {
                list = (List<Object>) method.invoke(delegate, args);
                invoke = list.size() > annotation.maxSizeList() ?
                        new ArrayList<Object>(list.subList(0, annotation.maxSizeList())) :
                        list;

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        } else {
            try {
                invoke = method.invoke(delegate, args);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return invoke;
    }


    //создаём ключ согласно значению аннотации identityBy
    private String key(Method method, Object[] args) {
        Class[] annoClasses = method.getAnnotation(Cache.class).identityBy();

        if (annoClasses[0].getTypeName().equals("java.lang.Object")) {
            return method.getName() + Arrays.toString(args);
        }

        List<Object> argsForKey = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < annoClasses.length; j++) {
                if (args[i].getClass().getTypeName().equals(annoClasses[j].getTypeName())) {
                    argsForKey.add(args[i]);
                    continue;
                }
                if (args[i].getClass().getTypeName().equals(primitiveToObject(annoClasses[j].getTypeName()))) {
                    argsForKey.add(args[i]);
                }

            }
        }
        return method.getName() + argsForKey.toString();
    }
    //т.к. в аргументе притивы упакованы, а из аннотации мы можем
    //получить имена примитивных типов, то создаём эквивалент для корректного сравнения
    private static String primitiveToObject(String primitive) {
        String packageName = "java.lang.";
        String capitalize = primitive.substring(0, 1).toUpperCase() +
                primitive.substring(1);
        if ("Int".equals(capitalize)) capitalize += "eger";
        if ("Char".equals(capitalize)) capitalize += "acter";

        return packageName + capitalize;

    }

}
