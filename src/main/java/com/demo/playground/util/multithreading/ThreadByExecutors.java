package com.demo.playground.util.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadByExecutors {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ex = Executors.newFixedThreadPool(3);
        System.out.println("3 threads created");
        for (int i = 0; i < 5; i++) {
            ex.execute(new Task(i));
        }
        ex.shutdown();
    }
}

class Task implements Runnable {
    private int id;
    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Inside Task");

        synchronized (this) {
            try {
                System.out.println("Waiting for task " + id + " to complete");
                wait(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

