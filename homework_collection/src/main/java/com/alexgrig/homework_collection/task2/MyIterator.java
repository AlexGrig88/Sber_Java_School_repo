package com.alexgrig.homework_collection.task2;

import java.util.Iterator;

public interface MyIterator<String> extends Iterator<String> {
    boolean hasPrevious();
    String previous();
}
