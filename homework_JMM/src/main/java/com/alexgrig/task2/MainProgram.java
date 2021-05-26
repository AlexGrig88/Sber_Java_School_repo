package com.alexgrig.task2;

public class MainProgram {
    public static void main(String[] args) {
        ExecutionManager manager = new ExecutionManagerImpl();
        MyTask[] tasks = {
                new MyTask(1L),
                new MyTask(1800L),
                new MyTask(5000L)
        };

        Context context = manager.execute(() -> {
            System.out.println("Я колбэк и я выполнился 1 раз после завершения всех тасков");
        }, tasks);

        System.out.println("Выполнено задач: " + context.getCompletedTaskCount());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Выполнено задач: " + context.getCompletedTaskCount());
        System.out.println("Выполнены или отменены все таски?: " + context.isFinished());

        try {
            Thread.sleep(2990);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Выполнены или отменены все таски?: " + context.isFinished());
    }
}

