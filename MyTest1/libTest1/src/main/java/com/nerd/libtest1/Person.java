package com.nerd.libtest1;

public class Person {

    int num;
    String name;
    String dept;
    String address;

    void print(){
        System.out.println("num : " + num);
        System.out.println("name : " + name);
        System.out.println("dept : " + dept);
        System.out.println("address : " + address);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
