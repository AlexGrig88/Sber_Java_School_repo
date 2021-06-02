package com.alexgrig.cachetools;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CacheInvocationHandler implements InvocationHandler {

    private final Object delegate;
    private final SyncMap<String, Object> cacheMap = new SyncMap<>(new HashMap<>());
    //private final ConcurrentHashMap<String, Object> cacheMap = new ConcurrentHashMap<>();
    private final String rootDirectory;
    private final int cacheSize;
    private ObjectOutputStream streamIfFileNotExists = null;
    boolean isInitialized = false;


    public CacheInvocationHandler(Object delegate, int cacheSize, String rootDirectory) {
        this.delegate = delegate;
        this.rootDirectory = rootDirectory;
        this.cacheSize = cacheSize;
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
            System.out.println("calculate...");
            Object invoke = null;
            if (method.getReturnType() == java.util.List.class) {
                List<Object> list = (List<Object>) method.invoke(delegate, args);
                invoke = list.size() > annotation.maxSizeList() ?
                        list.subList(0, annotation.maxSizeList()) :
                        list;
            } else {
                invoke = method.invoke(delegate, args);
            }
            cacheMap.put(key, invoke);
            return invoke;


        } else if (annotation.cacheType() == CacheType.FILE) {
            String shortFileName = annotation.fileNamePrefix().equals("default") ?
                    method.getName() :
                    annotation.fileNamePrefix();

            File file = new File(rootDirectory + "\\" + shortFileName + ".ser");

            initCacheMap(file);

            if (!file.exists()) {     //эта часть выполнится только при первом создании файла
                streamIfFileNotExists = new ObjectOutputStream(new FileOutputStream(file));
                isInitialized = true;
            }

            OutputStream fileOutputStream = new FileOutputStream(file, true);
            ObjectOutputStream oos = null;
            if (streamIfFileNotExists != null) {
                oos = streamIfFileNotExists;
            } else {
                oos = new AppendingObjectOutputStream(fileOutputStream);
            }

            if (cacheMap.containsKey(key)) {
                System.out.println("        Result from cache!");
                return cacheMap.get(key);
            }
            System.out.println("calculate...");
            Object invoke = null;
            if (method.getReturnType() == java.util.List.class) {
                List<Object> list = (List<Object>) method.invoke(delegate, args);
                invoke = list.size() > annotation.maxSizeList() ?
                        new ArrayList<Object>(list.subList(0, annotation.maxSizeList())) :
                        list;
            } else {
                invoke = method.invoke(delegate, args);
            }
            cacheMap.put(key, invoke);
            SerializationDataUnit unit = new SerializationDataUnit(key, invoke);
            oos.writeObject(unit);
            return invoke;
        } else {
            //недостижимый результат
            return null;
        }

    }

    //при первом запуске приложения и если файл уже существует выгружаем
    // из файла в наш динамический кеш закешированные результаты
    private void initCacheMap(File file) {
        if(!isInitialized && file.exists()) {
            isInitialized = true;
            try(FileInputStream fin = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fin))
            {
                SerializationDataUnit unit = null;
                while(true) {
                    unit = (SerializationDataUnit) ois.readObject();
                    cacheMap.put(unit.getKey(), unit.getResult());
                }
            } catch(EOFException ex) {
                System.out.println("****All object was loaded in cacheMap!*****");
            } catch(ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
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
