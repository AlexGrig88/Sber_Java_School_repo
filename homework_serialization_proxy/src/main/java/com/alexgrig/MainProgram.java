package com.alexgrig;

import com.alexgrig.businesslogic.CacheProxy;
import com.alexgrig.businesslogic.Service;
import com.alexgrig.businesslogic.ServiceImpl;

import java.io.IOException;
import java.util.Date;

public class MainProgram {

    public static void main(String[] args) throws IOException {
        String dir = "C:\\CacheFolder";
        CacheProxy cacheProxy = new CacheProxy(dir, 10);

        Service service = cacheProxy.cache(new ServiceImpl());

        System.out.println("======================================================");
        System.out.println("===============CacheType.IN_MEMORY====================");
        System.out.println(service.work("www", 3));
        System.out.println(service.work("zz", 6));
        System.out.println(service.work("www", 3));
        System.out.println(service.work("zz", 6));
        System.out.println(service.work("HOR", 20));

        System.out.println(service.work("zz", 6));

        System.out.println(service.work("zz", 6));
        System.out.println(service.work("HOR", 20));
        System.out.println(service.work("qt", 4));
        System.out.println(service.work("qt", 4));

        System.out.println("======================================================");
        System.out.println("=================CacheType.FILE=======================");

        System.out.println(service.run("Item", 2.5, new Date(14176453488L)));
        System.out.println(service.run("Mu", 11.7, new Date(15264532248L)));
        System.out.println(service.run("Item", 2.5, new Date(348L)));
        System.out.println(service.run("Mu", 11.7, new Date(15677645348L)));
        System.out.println(service.run("I", 6.5, new Date(4295645348L)));
        System.out.println(service.run("I", 6.5, new Date(4956L)));
        System.out.println(service.run("I", 6.5, new Date(49556566L)));
        System.out.println(service.run("I", 1.5, new Date(49556566L)));
        System.out.println(service.run("I", 1.5, new Date(496756566L)));


    }
}
