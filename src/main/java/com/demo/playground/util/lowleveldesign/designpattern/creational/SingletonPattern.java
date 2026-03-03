package com.demo.playground.util.lowleveldesign.designpattern.creational;

public class SingletonPattern {

    // 1. Private static volatile instance variable
    private static volatile SingletonPattern instance;

    // 2. Private constructor to prevent instantiation
    private SingletonPattern() {
        // Optional: Protect against reflection instantiation
        if (instance != null) {
            throw new IllegalStateException("Already initialized.");
        }
    }

    // 3. Public static method to get the instance
    public static SingletonPattern getInstance() {
        // Double-checked locking
        if (instance == null) {
            synchronized (SingletonPattern.class) {
                if (instance == null) {
                    instance = new SingletonPattern();
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton! Instance HashCode: " + System.identityHashCode(this));
    }

    public static void main(String[] args) {
        System.out.println("--- Basic Usage ---");
        SingletonPattern singleton1 = SingletonPattern.getInstance();
        singleton1.showMessage();

        System.out.println("\n--- Reference Check ---");
        SingletonPattern singleton2 = SingletonPattern.getInstance();
        if (singleton1 == singleton2) {
            System.out.println("singleton1 and singleton2 are the same instance.");
        } else {
            System.out.println("Different instances (Error in Singleton implementation).");
        }

        System.out.println("\n--- Thread Safety Check ---");
        Runnable task = () -> {
            SingletonPattern instance = SingletonPattern.getInstance();
            System.out.println("Thread " + Thread.currentThread().getName() + " got instance: " + System.identityHashCode(instance));
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");
        Thread thread3 = new Thread(task, "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
