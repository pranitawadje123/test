package com.example.test_service;

import java.io.IOException;

public class Child extends Parent {

    static String sts;

    Child() {
        super(sts);
        System.out.println("Child class constructor called ....");
    }

    public int show() throws IllegalArgumentException {
        System.out.println("Child show method...");
        return 1;
    }

    public void childMethod() {
        Child child = new Child();
    }

    @Override
    public void parentInstanceMethod() {
        sts = str1;
        System.out.println("Override parent instance method in child" + sts);
    }

    public void childInstanceMethod() {
        sts = str1;
        System.out.println("Child instance method in child" + sts);
    }
}
