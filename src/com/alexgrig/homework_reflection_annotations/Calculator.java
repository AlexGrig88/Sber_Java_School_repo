package com.alexgrig.homework_reflection_annotations;


import com.alexgrig.homework_reflection_annotations.task5_cache.Cache;
import com.alexgrig.homework_reflection_annotations.task6_logger.Metric;

public interface Calculator {
    @Cache
    @Metric
    int calc(int number);
}
