package com.example.myapplication.Controller;

import com.example.myapplication.Object.Cart;
import com.example.myapplication.Object.Products;

import java.util.ArrayList;
import java.util.List;

public class IntentKeys {
    public static final String EXTRA_USERNAME = "USERNAME";
    public static final String EXTRA_RESULT = "result";
    private static boolean isuser=false;

    public static boolean isIsuser() {
        return isuser;
    }

    public static void setIsuser(boolean isuser) {
        IntentKeys.isuser = isuser;
    }

    public static boolean addProduct(Cart cartfake){
        for(int i=0;i<listcart.size();i++){
            if(listcart.get(i).getIDP().equals(cartfake.getIDP()) ){
                int currentQuantity = Integer.parseInt(listcart.get(i).getQuantity());
                int newQuantity = currentQuantity + Integer.parseInt(cartfake.getQuantity());
                listcart.get(i).setQuantity(newQuantity);
                return true;
            }
        }
                return false;
    }
    public static List<Cart> listcart=new ArrayList<>();

    public static  void check(){

    }

    public  static String getQuantityItem(){
        return listcart.size()!=0?String.valueOf(listcart.size()):"0";
    }

    public static String getTotalBillCart(){
        String result="0";
        if(listcart.isEmpty()){
                return result;
            }
            int sum=0;
            for(int i=0;i<listcart.size();i++){
                sum=sum+listcart.get(i).getTotal();
            }
             result= String.valueOf(sum);
            return result;
    }

    public    static ArrayList<String> methodpayment =new ArrayList<>();

    //TEST
    public static   void setItem(){
        methodpayment=new ArrayList<>();
        methodpayment.add("Thanh toán khi nhận hàng");
        methodpayment.add("Thanh toán Zalopay");
        methodpayment.add("Thanh toán MoMo");
    }

}
