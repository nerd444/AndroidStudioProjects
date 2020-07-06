package com.nerd.libtest1;

public class Parent extends GrandParent{

    protected String job;

    public Parent() {
        System.out.println("parent class");
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("name : " + name);
        System.out.println("age : " + age);
        System.out.println("job : " + job);
    }
}
