package com.nerd.addmemo.model;

public class Contact {
    private int id;
    private String Title;
    private String Memo;

    public Contact(){

    }

    public Contact(int id, String Title, String Memo){
        this.id = id;
        this.Title = Title;
        this.Memo = Memo;
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
