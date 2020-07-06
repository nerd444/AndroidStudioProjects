package com.nerd.libtest1;

public class MemberService {

    boolean login(String id, String password){
        if(id.compareTo("hong") == 0 && password.compareTo("12345") == 0){
            System.out.println("successful login");
            return true;
        }else {
            System.out.println("fail login");
            return false;
        }
    }

    void logout(String id){
        System.out.println("Log Out.");
    }
}
