package com.demo.playground.util.multithreading;

public class ThreadByExtension {

    public static void main(String[] args) {
        System.out.println("Inside main Thread " + Thread.currentThread().getName());
        ThreadByExtensionTest t1 = new ThreadByExtensionTest();
        t1.start();
    }
}

class ThreadByExtensionTest extends Thread {

    @Override
    public void run() {
        System.out.println("Inside Thread " + Thread.currentThread().getName());
    }
}
