package com.nerd.firecontact.model;

import java.io.Serializable;

public class Contact implements Serializable {

    private String id;
    private String name;
    private String phone_number;

    public Contact(){

    }


    public Contact(String id, String name, String phone_number) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
    }

    public Contact(String name, String phone_number) {
        this.name = name;
        this.phone_number = phone_number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
