package com.demo.playground.test;

public class SingletonTest {

    private static SingletonTest singletonTest;

    public static synchronized SingletonTest getInstance() {
        if(singletonTest == null) {
            return new SingletonTest();
        }
        return singletonTest;
    }
}
