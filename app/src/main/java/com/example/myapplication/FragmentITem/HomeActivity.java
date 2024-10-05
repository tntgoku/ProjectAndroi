package com.example.myapplication.FragmentITem;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.Adapter.MyAdapter;
import com.example.myapplication.Adapter.SliderAdapter;
import com.example.myapplication.Controller.IntentKeys;
import com.example.myapplication.MySql.ConnectDB;
import com.example.myapplication.Object.Cart;
import com.example.myapplication.Object.Products;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Fragment {

    private RecyclerView rcyl;
    private ImageView img;
    private TextView txtcountitem;
    private ViewPager2 viewpag2;
    ConnectDB db=new ConnectDB();
    FragmentHomeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding= FragmentHomeBinding.inflate(inflater, container, false);
        setID();
        eVentID();
        db.conn=db.getConn();
        if(IntentKeys.listcart!=null && IntentKeys.listcart.size()>0){
            Log.d("TAG",IntentKeys.listcart.isEmpty()?"Null":"awdad");

            for(Cart i: IntentKeys.listcart){
            Log.d("CART ","ID: "+i.getIDP()+"\n"+"Quantity:"+i.getQuantity()+"\nCost: "+String.valueOf(i.getCost()));
            }
        }else{
            Log.d("TAG", "Loi chay Cart");
        }

       return  binding.getRoot();
    }
    
    void setID(){
        rcyl=binding.recycles;
        txtcountitem=binding.quanittynotice;
        img=binding.imageView2;
        viewpag2=binding.viewPager2;
    }


    // Show
    void eVentID(){
        rcyl.setAdapter(new MyAdapter(getInfor(),binding.getRoot().getContext()));

        rcyl.setLayoutManager(new GridLayoutManager(binding.getRoot().getContext(), 2));
            if(IntentKeys.listcart.isEmpty()){
                img.setVisibility(View.INVISIBLE);
                txtcountitem.setVisibility(View.INVISIBLE);
            }else{
                img.setVisibility(View.VISIBLE);
                txtcountitem.setVisibility(View.VISIBLE);
                txtcountitem.setText(IntentKeys.getQuantityItem());
            }

            SetSlideItem();
    }

//    Show all item Product with Recyclevew
    public List<Products> getInfor(){
        List<Products> mlist=new ArrayList<>();
      db.conn=db.getConn();
      String[] rows= db.Query("*", "Product").split("\n");
        for(int i=0;i<9;i++){
            for(String row: rows){
                    String[] a=row.split("\t");

                mlist.add(new Products(
                        a[0],
                        a[1],
                        "null",
                        Integer.parseInt(a[4]),
                        Integer.parseInt(a[5]),
                        Integer.parseInt(a[3]),
                        Integer.parseInt(a[7].trim())));
            Log.d("Product","ID:"+a[0]+"\n"+"Name:"+a[1]+"\n"+a[2]+"\n"+a[3]+"\n"+a[4]+"\n"+a[5]+"\n"+a[6]+"\n"+a[7]+"a[7] thanh kieu int"+Integer.parseInt(a[7].trim()) );
            }
        }

        return mlist;
    }



    private  void SetSlideItem(){
            List<Integer> mlist=new ArrayList<>();
            mlist.add(R.drawable.item1);
            mlist.add(R.drawable.item2);
            mlist.add(R.drawable.item3);
            mlist.add(R.drawable.item4);
            mlist.add(R.drawable.item5);
            mlist.add(R.drawable.item6);

        SliderAdapter as=new SliderAdapter(mlist);
        viewpag2.setAdapter(as);
    }
}