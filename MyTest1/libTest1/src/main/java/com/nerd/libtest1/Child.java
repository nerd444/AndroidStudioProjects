package com.nerd.libtest1;

public class Child extends Parent {

    public String hobby;

    public Child() {
        System.out.println("child class");
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("name : " + name);
        System.out.println("age : " + age);
        System.out.println("job : " + job);
        System.out.println("hobby : " + hobby);
    }
}
