package com.nerd.libtest1;

public class ClassTest_2 extends ClassTest_1{

    String department;

    ClassTest_2(String name, int salary, String department) {
        super(name, salary);
        this.department = department;
    }

    @Override
    public void getInformation() {
        String name = super.getName();
        int salary = super.getSalary();
        System.out.println("name : " + name + " salary : " + salary + " department : " + department);
    }
}
