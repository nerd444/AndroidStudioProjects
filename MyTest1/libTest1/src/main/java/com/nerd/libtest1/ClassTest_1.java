package com.nerd.libtest1;

public class ClassTest_1 {

    private String name;
    private int salary;

    public ClassTest_1(){

    }
    ClassTest_1(String n, int s){
        name = n;
        salary = s;
    }

    public String getName(){
        return name;
    }
    public int getSalary(){
        return salary;
    }
    public void getInformation(){
        System.out.println("name : " + name + " salary : " + salary);
    }
    public void prn(){
        System.out.println("super class");
    }
}
