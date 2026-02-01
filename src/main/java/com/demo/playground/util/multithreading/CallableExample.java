package com.demo.playground.util.multithreading;

import java.util.concurrent.*;

public class CallableExample {

    public static void main(String[] args) {
        System.out.println("Inside main Thread " + Thread.currentThread().getName());
        CallableTest t1 = new CallableTest("Thread1");
        CallableTest t2 = new CallableTest("Thread2");
        ExecutorService ex = Executors.newFixedThreadPool(2);
        Future<String> result1 = ex.submit(t1);
        Future<String> result2 = ex.submit(t2);
        try {
            System.out.println(result1.get());
            System.out.println(result2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}



class CallableTest implements Callable<String> {
    private String arg;
    public CallableTest(String arg) {
        this.arg = arg;
    }

    @Override
    public String call() throws Exception {
        StringBuilder sb = new StringBuilder("Running " + arg);
        return sb.toString();
    }
}
