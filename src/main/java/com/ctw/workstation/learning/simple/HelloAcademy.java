package com.ctw.workstation.learning.simple;

public class HelloAcademy {
    Integer counter;

    public void initialize() {
        counter = 0;
    }
    public String sayHello(String name) {

        if (name != null) {
            counter++;
            return "Hello " + name;
        } else {
//            return "Hello";
            throw new IllegalArgumentException("Name cannot be null");

        }
    }
}
