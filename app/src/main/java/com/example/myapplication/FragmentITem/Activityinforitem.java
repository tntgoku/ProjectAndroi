package com.example.myapplication.FragmentITem;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.ActivityiTem.MainActivity2;
import com.example.myapplication.ActivityiTem.OrderCart;
import com.example.myapplication.Adapter.imgAdapter;
import com.example.myapplication.Controller.IntentKeys;
import  com.example.myapplication.Controller.eventSystem;
import com.example.myapplication.Object.Cart;
import com.example.myapplication.Object.Products;
import com.example.myapplication.Object.User;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityinforitemBinding;
import com.example.myapplication.databinding.SelectbuysBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Activityinforitem extends AppCompatActivity implements eventSystem {
    private ImageView imgbtn,imgbtn1,imgbtn2;
    private Button btn,btn1,btn2;
    private  Button dialogbtn,dialogbtn1,dialogbtn2;
    private  View mview;
    private ViewPager2 viewimg;
    SelectbuysBinding selectbuysBinding;
    ActivityinforitemBinding binding;
    private TextView[] ar;
    Products pr;
    String a=null;
    private  int quantitybuy=1;
    private AlertDialog.Builder builder;
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
        imgbtn=binding.backbtn;
        imgbtn1=binding.likebtn;
        imgbtn2=binding.cartbtn;
        btn=binding.buyobject;
        btn1=binding.addcarts;
        ar[0]=binding.titleobject1;
        ar[1]=binding.costobect1;
        ar[2]=binding.noticeobject1;
        viewimg=binding.imgviewpage;
        ar[3]=binding.countitem;
        if(IntentKeys.listcart==null){
            IntentKeys.listcart=new ArrayList<>();
        }


        //FireBase
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        DatabaseReference myRef= database.getReference("User");
        User as=new User("KH001","Triệu Trung Hiếu","19/03/03","Nam","Đồng Đăng-Lạng Sơn","1234567890","admin1","123456","trunghieuhsdd1@gmail.com");
        String Name =as.getName().substring(as.getName().length()-5);
        myRef.child(Name).setValue(as).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                // Upload Successfull
                Log.d("Firebase", "Dữ liệu đã được đẩy lên thành công");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Thông báo thất bại
                        Log.d("Firebase", "Đẩy dữ liệu thất bại", e);
                    }
                });
    }

    @Override
    public void eVentID() {

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent get=getIntent();
       pr=(Products)get.getSerializableExtra("itemObject");
       a=get.getStringExtra("Id");
        //TextView setText
        Log.i("INFOR",get.getStringExtra("ID INFOR ")+"\n"+pr.getIDP());
        ar[0].setText(pr.getNameP());
      String cost=  String.valueOf(pr.getCost()==0?"Het hang":pr.getCost());
        ar[1].setText(cost);
        ar[2].setText(pr.getNote());

        if (!CanBuy()) {
            btn.setEnabled(false); // Vô hiệu hóa nút
            btn.setText("Hết hàng.");
            int color = ContextCompat.getColor(binding.getRoot().getContext(), R.color.gray);
            btn.setBackgroundColor(color); // Đổi màu nền thành xám (hoặc một màu khác)
        } else {
            btn.setEnabled(true);
            btn.setText("Mua"); // Thiết lập lại văn bản nút
            btn.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.design_default_color_primary)); // Đặt lại màu nền
            btn1.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.design_default_color_primary)); // Đặt lại màu nền
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialogView();
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

//        To cart

    }
    private  void addCart(){
        LocalDateTime currentTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        Cart giohang=new Cart(a,1,formattedTime,pr.getCost());
            if(IntentKeys.addProduct(giohang)){
                Log.d("TAG addTocart","Success");
            }else{
                IntentKeys.listcart.add(giohang);
                Log.d("TAG addTocart","Them sp moi vao gio hang voi id:"+giohang.getIDP());

            }


        Log.d("TAG", "addCart: "+IntentKeys.listcart.get(0).getCost()+"\n"
                +
                IntentKeys.listcart.get(0).getQuantity());
            for(int i=0;i<IntentKeys.listcart.size();i++){
                Log.d("TAG", "addCart:"+IntentKeys.listcart.get(i).getQuantity());
                Log.d("TAG", "addCart:"+IntentKeys.listcart.get(i).getIDP());
            }
            String slgiohang= String.valueOf(IntentKeys.listcart.size()==0?0:IntentKeys.listcart.size());
            ar[3].setText(slgiohang);
    }

    private  boolean CanBuy(){
            if(pr.getQuantity()!=0)
                return true;
            return false;
    }

    private  void opendialogView(){
        Dialog dialog=new Dialog(Activityinforitem.this);
        selectbuysBinding = SelectbuysBinding.inflate(getLayoutInflater());
        dialog.setContentView(selectbuysBinding.getRoot());

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        ImageView img=dialog.findViewById(R.id.imgproductbuys);
Log.i("TAG11", String.valueOf(pr.getImg()));
        img.setImageResource(pr.getImg());
        TextView t,t1,t2,t3,t4;
        t=dialog.findViewById(R.id.titleobject2);
        t1=dialog.findViewById(R.id.quantityitem);
        t2=dialog.findViewById(R.id.textView5);
        t3=dialog.findViewById(R.id.minus1);
        t4=dialog.findViewById(R.id.plus1);
        t.setText(pr.getNameP());
        t1.setText(String.valueOf(pr.getQuantity()));
        dialogbtn=selectbuysBinding.agree;


        dialogbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activityinforitem.this, OrderCart.class);
                i.putExtra("quantity",t2.getText().toString());
                i.putExtra("product",pr);
                    startActivity(i);
            }
        });
//        QuantityBuy-1
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantitybuy!=1 ){
                    quantitybuy=quantitybuy-1;
                    t2.setText(String.valueOf(quantitybuy));
                }
            }
        });

//        QuantityBuy+1
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(quantitybuy >pr.getQuantity()){
                    builder=new AlertDialog.Builder(Activityinforitem.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Số lượng hàng trong kho không đủ!!!!");
                    builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.show();
                }else{
                    quantitybuy=quantitybuy+1;
                    t2.setText(String.valueOf(quantitybuy));
                }
            }
        });
        t2.setText(String.valueOf(quantitybuy));
        dialog.show();
    }

}