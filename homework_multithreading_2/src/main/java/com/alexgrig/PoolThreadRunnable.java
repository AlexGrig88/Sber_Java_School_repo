package com.alexgrig;

import java.util.concurrent.BlockingQueue;

public class PoolThreadRunnable implements Runnable {

    private Thread thread;
    private BlockingQueue taskQueue;  //разделяемый ресурс Queue<Runnable> taskQueue = new LinqedList<>()  //synchronized
    private boolean isStopped;

    public PoolThreadRunnable(BlockingQueue queue, int minThreads, int maxThreads){
        taskQueue = queue;
    }
    public PoolThreadRunnable(BlockingQueue queue){
        taskQueue = queue;
    }

    public synchronized Thread getThread() {
        return thread;
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        while(!isStopped()){
            try{
                System.out.println("Start run " + thread.getName());
                Runnable runnable = (Runnable) taskQueue.take();
                runnable.run();
                System.out.println("End run " + thread.getName());
            } catch(InterruptedException e){
                //log or otherwise report exception,
                //but keep pool thread alive.
                System.out.println("End run " + thread.getName());
            }
        }
    }
    public synchronized void doStop(){
        isStopped = true;
        //break pool thread out of dequeue() call.
        this.thread.interrupt();
    }

    public synchronized boolean isStopped(){
        return isStopped;
    }

}
