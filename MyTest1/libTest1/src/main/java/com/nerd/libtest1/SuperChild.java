package com.nerd.libtest1;

public class SuperChild extends SuperParent {

    int a = 20;

    @Override
    public void print(){
        System.out.println("SuperChild print()");
        System.out.println("a = " + a);
        System.out.println();
    }

    public void superCall(){
        System.out.println("super.a = " + super.a);
        super.print();
    }

}
