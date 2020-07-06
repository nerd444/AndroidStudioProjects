package com.nerd.libtest1;

public class Professor extends Person {

    private String[] subjects;

    public void print_subject(){
        System.out.println(name + "'s subjects");
        for(int i = 0; i < subjects.length; i++){
            System.out.println("subject : " + subjects[i]);
        }
    }

    public String[] getSubjects() {
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }
}
