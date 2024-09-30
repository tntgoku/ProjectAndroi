package com.example.myapplication.Object;

public class People {
    String IDP,Name,Date,Gender,Address,Phone;

    public People() {
    }

    public People(String IDP, String name, String date, String gender, String address, String phone) {
        this.IDP = IDP;
        Name = name;
        Date = date;
        Gender = gender;
        Address = address;
        Phone = phone;
    }

    public String getIDP() {
        return IDP;
    }

    public void setIDP(String IDP) {
        this.IDP = IDP;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
