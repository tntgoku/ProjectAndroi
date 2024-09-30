package com.example.myapplication.FragmentITem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.Adapter.imgAdapter;
import com.example.myapplication.Controller.IntentKeys;
import  com.example.myapplication.Controller.eventSystem;
import com.example.myapplication.Object.Cart;
import com.example.myapplication.Object.Products;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityinforitemBinding;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Activityinforitem extends AppCompatActivity implements eventSystem {
    private ImageView imgbtn,imgbtn1,imgbtn2;
    private Button btn,btn1,btn2;
    private  View mview;
    private ViewPager2 viewimg;
    ActivityinforitemBinding binding;
    private TextView[] ar;
    Products pr;
    private IntentKeys unit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activityinforitem);
        binding = ActivityinforitemBinding.inflate(getLayoutInflater());
        // Đặt nội dung của view từ binding
        setContentView(binding.getRoot());
        setID();
        eVentID();

    }
    @Override
    public void setID(){
        ar =new TextView[6];
        imgbtn=findViewById(R.id.backbtn);
        imgbtn1=findViewById(R.id.likebtn);
        imgbtn2=findViewById(R.id.cartbtn);
        btn=findViewById(R.id.buyobject);
        btn1=findViewById(R.id.addcarts);
        ar[0]=findViewById(R.id.titleobject1);
        ar[1]=findViewById(R.id.costobect1);
        ar[2]=findViewById(R.id.noticeobject1);
        viewimg=binding.imgviewpage;
        ar[3]=binding.countitem;
        if(IntentKeys.listcart==null)
            unit.listcart=new ArrayList<>();


    }

    @Override
    public void eVentID() {
        Intent get=getIntent();
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

       pr=(Products)get.getSerializableExtra("itemObject");
        //TextView setText
        ar[0].setText(pr.getNameP());
      String cost=  String.valueOf(pr.getCost()==0?"Het hang":pr.getCost());
        ar[1].setText(cost);
        ar[2].setText(pr.getNote());

//        List<Integer> imageList = new ArrayList<>();

//             imageList.add(   pr.getImg());  // Assuming this returns a List of drawable resource IDs
//        imgAdapter imgAdapter1 = new imgAdapter(this, imageList);
//        viewimg.setAdapter(imgAdapter1);
        //
        if (pr.getCost() == 0) {
            btn.setEnabled(false); // Vô hiệu hóa nút
            btn.setText("Giá trị bằng 0"); // Có thể thay đổi văn bản của nút
            btn.setBackgroundColor(getResources().getColor(R.color.gray)); // Đổi màu nền thành xám (hoặc một màu khác)
        } else {
            btn.setEnabled(true); // Bật nút nếu giá trị khác 0
            btn.setText("Mua"); // Thiết lập lại văn bản nút
            btn.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.design_default_color_primary)); // Đặt lại màu nền
            btn1.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.design_default_color_primary)); // Đặt lại màu nền

        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCart();

            }
        });
        ar[3].setText(String.valueOf(IntentKeys.listcart.size()));

        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Activityinforitem.this,Fragment_1.class);
                i.putExtra("item",pr);
                startActivity(i);
            }
        });
    }
    private  void addCart(){
        LocalDateTime currentTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        if(IntentKeys.listcart.size()>0){
            int sl=1;
            for(int i=0;i<IntentKeys.listcart.size();i++){

            }
        }
        Cart giohang=new Cart(pr.getIDP(),1,formattedTime,pr.getCost());
            IntentKeys.listcart.add(giohang);

        Log.d("TAG", "addCart: "+IntentKeys.listcart.get(0).getCost());
    }

}