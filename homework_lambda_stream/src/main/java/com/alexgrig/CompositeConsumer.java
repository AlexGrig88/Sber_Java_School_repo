package com.alexgrig;

import java.util.function.Consumer;

@FunctionalInterface
public interface CompositeConsumer<T> {
    void generate(Consumer<T> consumer);
}
