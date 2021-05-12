package com.alexgrig.businesslogic;

import com.alexgrig.cachetools.CacheInvocationHandler;

import java.lang.reflect.Proxy;

public class CacheProxy {

    private String rootDirectory;
    private int cacheSize;

    public CacheProxy(String rootDirectory, int cacheSize) {
        this.rootDirectory = rootDirectory;
        this.cacheSize = cacheSize;
    }

    public Service cache(Service original) {
        Service proxyService = (Service) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                original.getClass().getInterfaces(),
                new CacheInvocationHandler(original, cacheSize, rootDirectory));
        return proxyService;
    }

}
