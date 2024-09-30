package com.example.myapplication.Object;

public class Cart {
    private String IDP,DateBuy;
    private  int Quantity;
    private  int Cost;
    private  Products sa;
    public Cart() {
    }

    public Cart(String IDP,int  quantity, String dateBuy, int cost) {
        this.IDP = IDP;
        Quantity = quantity;
        DateBuy = dateBuy;
        Cost = cost;
    }

    public String getIDP() {
        return IDP;
    }

    public void setIDP(String IDP) {
        this.IDP = IDP;
    }

    public String getQuantity() {
        return String.valueOf(Quantity);
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getDateBuy() {
        return DateBuy;
    }

    public void setDateBuy(String dateBuy) {
        DateBuy = dateBuy;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }
}
