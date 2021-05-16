package com.alexgrig;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class Streams<T> {

    private CompositeConsumer<T> compositeConsumer;

    private Streams(CompositeConsumer<T> compositeConsumer) {
        this.compositeConsumer = compositeConsumer;
    }

    public static <T> Streams<T> of(Collection<T> collection) {
        return new Streams<>(consumer -> collection.forEach(item -> consumer.accept(item)));
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        return new Streams<>(consumer -> compositeConsumer.generate(item -> {
            if (predicate.test(item))
                consumer.accept(item);
        }));
    }

    public <R> Streams<R> transform(Function<? super T, ? extends R> mapper) {
        return new Streams<>(consumer -> compositeConsumer.generate(item ->
                consumer.accept(mapper.apply(item))
        ));
    }

    public Streams<T> limit(long maxSize) {
        final long[] counter = {maxSize};
        return new Streams<>(consumer -> compositeConsumer.generate(item -> {
            if(counter[0] > 0) {
                consumer.accept(item);
                --counter[0];
            }
        }));
    }


    public <K, U> Map<K, U> toMap(Function<? super T, ? extends K> keyMapper,
                                  Function<? super T, ? extends U> valueMapper) {
        Map<K, U> map = new HashMap<>();
        compositeConsumer.generate(item -> map.put(keyMapper.apply(item), valueMapper.apply(item)));
        return map;
    }

    public void foreach(Consumer<? super T> consumer) {
        compositeConsumer.generate(item -> consumer.accept(item));
    }
}
