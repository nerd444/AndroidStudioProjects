package com.nerd.libtest1;

public class ChildAirplane extends Airplane {

    int flyMode = 1;

    @Override
    void fly() {
        if(flyMode == 1){
            super.fly();
        }else{
            System.out.println("Child Flying.");
        }
    }
}
