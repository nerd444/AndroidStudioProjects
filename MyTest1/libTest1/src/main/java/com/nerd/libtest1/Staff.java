package com.nerd.libtest1;

import java.security.PrivateKey;

public class Staff extends Person {

    private String job;

    public void print_job(){
        System.out.println(name + "'s job : " + job);
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
