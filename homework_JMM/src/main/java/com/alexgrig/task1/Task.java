package com.alexgrig.task1;

import java.util.concurrent.Callable;

public class Task<T> implements Runnable {

    private Callable<? extends T> callable;
    private volatile T result;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }


    public T get() throws MyRuntimeException {
        if (result == null) {
            synchronized (this) {
                if (result == null) {
                    System.out.println("Первым Выполнил Callable поток: " + Thread.currentThread().getName());
                    try {
                        result = callable.call();
                    } catch (Exception e) {
                        throw new MyRuntimeException("Произошла ошибка в callable - объекте. Результат не получен.");
                    }
                    return result;
                }
                System.out.println("В потоке " + Thread.currentThread().getName() + " вернётся уже посчитанный результат в синхронизации");
                return result;
            }
        }
        System.out.println("В потоке " + Thread.currentThread().getName() + " вернётся ррезультат вне синхронизации");
        return result;
    }

    @Override
    public void run() {
        try {
            this.get();
            // System.out.println("Result = " + result);
        } catch (MyRuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
