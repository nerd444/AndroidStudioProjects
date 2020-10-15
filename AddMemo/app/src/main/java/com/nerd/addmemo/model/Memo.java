package com.nerd.addmemo.model;

public class Memo {
    private int id;
    private String Title;
    private String Memo;

    public Memo(){

    }

    public Memo(int id, String title, String memo) {
        this.id = id;
        Title = title;
        Memo = memo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String memo) {
        Memo = memo;
    }
}
