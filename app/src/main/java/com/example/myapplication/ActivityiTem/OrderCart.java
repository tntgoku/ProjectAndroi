package com.example.myapplication.ActivityiTem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.listviewitembuy;
import com.example.myapplication.Controller.IntentKeys;
import com.example.myapplication.Controller.eventSystem;
import com.example.myapplication.Object.Cart;
import com.example.myapplication.Object.Products;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityOrderBinding;

import java.util.ArrayList;
import java.util.List;

public class OrderCart extends AppCompatActivity implements eventSystem {
    ActivityOrderBinding binding;

    Button btn,btn1,btn2;
    RecyclerView rec;
    EditText ed,ed1,ed2,ed3,ed4;
    TextView tv,tv1,tv2,tv3,tv4;
    Spinner sp,sp1;
    TableRow tb,tb1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent is=getIntent();
       Products ka=(Products) is.getSerializableExtra("product");

        Log.d("INFOR INTENT",ka.getNameP()+"\n"+ka.getCost()+"\n"+ka.getQuantity()+"\n"+is.getStringExtra("quantity"));
        setID();
        eVentID();
    }

    @Override
    public void setID() {
//        Button
        btn=binding.exitorder;
        btn1=binding.orderbill;

//        EditText
        ed=binding.editName;
        ed1=binding.editAddress;
        ed2=binding.editPhone;
        ed3=binding.editemail;

//        Spinner
        sp=binding.spmethod;
        sp1=binding.methodr;

//        TableRow
        tb=binding.billdiscount;


//        TextView
        tv=binding.totalbill;
        tv1=binding.totaldiscount;


//        RecyclerView
        rec=binding.listproduct;
    }

    @Override
    public void eVentID() {
            tb.setVisibility(View.INVISIBLE);
        IntentKeys.methodpayment.add("COD");
        IntentKeys.methodpayment.add("Payment");
        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<>(OrderCart.this, R.layout.spinneritem, IntentKeys.methodpayment);
            sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Xử lý khi một item được chọn
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(OrderCart.this,"Select item: "+selectedItem,Toast.LENGTH_LONG).show();
                // Làm gì đó với selectedItem
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý khi không có item nào được chọn
            }
        });


            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            setAdapter();
    }

    private  void setAdapter(){
        List<Products> mcartbuy=new ArrayList<>();
        Intent i=getIntent();
        Products mProduct =(Products)i.getSerializableExtra("product");
        int quantity=Integer.parseInt(i.getStringExtra("quantity"));
        mProduct.setQuantity(quantity);
        mcartbuy.add(mProduct);
        listviewitembuy mlist=new listviewitembuy(mcartbuy);
        rec.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rec.setAdapter(mlist);
        if(mcartbuy.size()!=0){
            for(int i1=0;i1<mcartbuy.size();i1++){
                int total=mcartbuy.get(i1).getCost()*mcartbuy.get(i1).getQuantity();
                tv.setText(String.valueOf(total));
            }

        }
    }
}
