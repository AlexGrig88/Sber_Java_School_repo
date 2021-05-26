package com.alexgrig.task1;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainProgram {
    public static void main(String[] args) {

        Task<String> task = new Task<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String result = "default Value";
                try {
                    Thread.sleep(300);
                    if (new Random().nextInt(200) == 13) {  //имитация случая возникновения ексепшена при расчёте
                        throw new Exception();
                    }
                    result = "Hello - result of Task";
                    return result;
                } catch (InterruptedException e) {
                    System.out.println("Поток прерван. Возвращено дефолтное значение");
                    return result;
                }
            }
        });
        //task.get();
        ExecutorService service = Executors.newFixedThreadPool(4);


        service.execute(task);
        service.execute(task);

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.execute(task);

        String result = "";
        try {
           result = task.get();
        } catch (MyRuntimeException ex) {
            System.out.println(ex.getMessage());
            result = "default";
        }
        new Thread(task).start();

        System.out.println(result + "  из потока " + Thread.currentThread().getName());


        service.shutdown();
    }
}
