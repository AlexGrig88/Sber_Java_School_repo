package com.alexgrig;

import java.util.List;

public interface Calculable {

    @Cacheable(PostgresSource.class)
    List<Integer> fibonachi(int n);
}
