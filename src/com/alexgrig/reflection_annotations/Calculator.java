package com.alexgrig.reflection_annotations;


import com.alexgrig.reflection_annotations.task5_cache.Cache;
import com.alexgrig.reflection_annotations.task6_logger.Metric;

public interface Calculator {
    @Cache
    @Metric
    int calc(int number);
}
