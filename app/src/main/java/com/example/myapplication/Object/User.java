package com.example.myapplication.Object;

public class User extends People{
    String username,pwd,email;

    public User() {
    }

    public User(String IDP, String name, String date, String gender, String address, String phone, String username, String pwd, String email) {
        super(IDP, name, date, gender, address, phone);
        this.username = username;
        this.pwd = pwd;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
