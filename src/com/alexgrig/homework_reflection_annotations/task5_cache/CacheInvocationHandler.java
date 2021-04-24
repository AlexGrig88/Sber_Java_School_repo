package com.alexgrig.homework_reflection_annotations.task5_cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class CacheInvocationHandler implements InvocationHandler {

    private final Object delegate;
    private Map<Integer, Object> cacheMap = new HashMap<>();

    public CacheInvocationHandler(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Cache.class)) {
            return method.invoke(delegate, args);
        }
        /* В случае если у нас метод имеет не один параметр:
           уникальный ключ для Map можно получать преобразовав массив аргументов в строку
           String key = Arrays.toString(args);
        */
        Integer key = (Integer) args[0];
        if (cacheMap.containsKey(key)) {
            System.out.println("        Результат из кэша!");
            return cacheMap.get(key);
        }
        System.out.println("вычисляется...");
        Object invoke = method.invoke(delegate, args);
        cacheMap.put(key, invoke);
        return invoke;
    }
}
