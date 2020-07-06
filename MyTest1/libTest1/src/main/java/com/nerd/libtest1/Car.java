package com.nerd.libtest1;

public class Car {
    String company = "Benz";
    String model = "S350";
    String color = "black";
    int maxSpeed = 350;
    int speed;

    Car(int speed){
        this.speed = speed;
    }

    Car(String company, String model, String color){
        this.company = company;
        this.model = model;
        this.color = color;
    }

    void print(){
        System.out.println("company : " + company);
        System.out.println("model : " + model);
        System.out.println("color : " + color);
        System.out.println("maxSpeed : " + maxSpeed);
        System.out.println("speed : " + speed);
    }

}
