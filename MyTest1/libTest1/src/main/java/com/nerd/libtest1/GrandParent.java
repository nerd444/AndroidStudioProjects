package com.nerd.libtest1;

public class GrandParent {

    protected String name;
    protected int age;

    public GrandParent(){
        System.out.println("GrandParent class");
    }

    public void printInfo(){
        System.out.println("name : " + name);
        System.out.println("age : " + age);
    }
}
