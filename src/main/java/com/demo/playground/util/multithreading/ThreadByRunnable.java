package com.demo.playground.util.multithreading;

public class ThreadByRunnable {
    public static void main(String[] args) {
        System.out.println("Inside main Thread " + Thread.currentThread().getName());
        ThreadByRunnable1 t1 = new ThreadByRunnable1();
        Thread t = new Thread(t1);
        t.start();
    }
}

class ThreadByRunnable1 implements Runnable {

    @Override
    public void run() {
        System.out.println("Inside main Thread " + Thread.currentThread().getName());
    }
}
