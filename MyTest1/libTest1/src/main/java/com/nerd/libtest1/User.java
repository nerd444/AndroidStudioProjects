package com.nerd.libtest1;

public class User {

    public String nickname;
    private String email;
    private String password;

    User(String nickname){
        this.nickname = nickname;
        System.out.println("nickname : " + nickname);
    }

    boolean setEmail(String email){
        if(email.contains("@")){
            this.email = email;
            System.out.println("email : " + email);
            return true;
        }else{
            return false;
        }
    }

    boolean setPassword(String password1, String password2){
        if(password1.compareTo(password2) == 0){
            if(password1.length() <= 6 || password1.length() >= 12){
                return false;
            }else{
                this.password = password1;
                System.out.println("password : " + password);
                return true;
            }
        }else{
            return false;
        }
    }

    void print(){
        System.out.println("nickname : " + nickname);
        System.out.println("email : " + email);
        System.out.println("password : " + password);
    }

}
