package com.alexgrig;

public interface ThreadPool {

    void start();           // запускает потоки. Потоки бездействуют, до тех пор
                             // пока не появится новое задание в очереди (см. execute)
    void execute(Runnable runnable) throws Exception;
                                    // складывает это задание в очередь.
                                    // Освободившийся поток должен выполнить
                                    // это задание. Каждое задание должны быть выполнено ровно 1 раз
}
