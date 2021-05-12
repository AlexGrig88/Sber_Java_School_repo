package com.alexgrig.cachetools;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    CacheType cacheType() default CacheType.IN_MEMORY;
    int maxSizeList() default 1000;
    String fileNamePrefix() default "default";
    boolean zip() default false; //указывается для CacheType.FILE

    Class[] identityBy() default {Object.class};//определение уникальности результата по типу аргумента

}
