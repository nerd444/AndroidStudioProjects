package com.nerd.libtest1;

public class PrivateMember {

    public static final int NUMBER = 100;

    int a = 10;

    final void speedUp(){
        a = a + 10;
    }

    void speedDown(){
        a = a - 10;
    }

}
