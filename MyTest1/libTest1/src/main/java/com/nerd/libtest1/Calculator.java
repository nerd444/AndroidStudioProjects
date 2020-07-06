package com.nerd.libtest1;

public class Calculator {

    void powerOn(){
        System.out.println("Power On");
    }

    int plus(int a, int b, int c){
        int ret = a + b + c;
        return ret;
    }

    double divide(int a, int b){
        double result = (double)a / b;
        return result;
    }



}
