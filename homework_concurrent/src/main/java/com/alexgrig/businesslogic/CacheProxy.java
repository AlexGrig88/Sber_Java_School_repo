package com.alexgrig.businesslogic;

import com.alexgrig.cachetools.CacheInvocationHandler;

import java.lang.reflect.Proxy;

public class CacheProxy {

    private String rootDirectory;


    public CacheProxy(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public Service cache(Service original) {
        Service proxyService = (Service) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                original.getClass().getInterfaces(),
                new CacheInvocationHandler(original, rootDirectory));
        return proxyService;
    }

}
