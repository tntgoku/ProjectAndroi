package com.example.myapplication.Object;

import java.io.Serializable;

public class Products implements Serializable {
    private  String IDP,nameP,Note;
    private  int Cost,Discount,Quantity,img;

    public Products()  {
    }

    public Products(String IDP, String nameP, String note, int cost, int discount, int quantity, int img) {
        this.IDP = IDP;
        this.nameP = nameP;
        Note = note;
        Cost = cost;
        Discount = discount;
        Quantity = quantity;
        this.img = img;
    }

    public String getIDP() {
        return IDP;
    }

    public void setIDP(String IDP) {
        this.IDP = IDP;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int discount) {
        Discount = discount;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
