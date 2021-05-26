package com.alexgrig.task2;

public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
