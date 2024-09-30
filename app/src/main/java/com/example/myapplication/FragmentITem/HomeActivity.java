package com.example.myapplication.FragmentITem;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.MyAdapter;
import com.example.myapplication.MySql.ConnectDB;
import com.example.myapplication.Object.Products;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Fragment {

    private RecyclerView rcyl;
    View mview;
    ConnectDB db=new ConnectDB();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       mview= inflater.inflate(R.layout.fragment_home, container, false);
        setID();
        eVentID();
        db.conn=db.getConn();
       return  mview;
    }
    void setID(){
        rcyl=mview.findViewById(R.id.recycles);

    }

    void eVentID(){
        rcyl.setAdapter(new MyAdapter(getInfor(),mview.getContext()));
        int[] aimg={R.drawable.item0_1,R.drawable.item0_2,R.drawable.item0,R.drawable.item1
                ,R.drawable.item2,R.drawable.item3,R.drawable.item4,R.drawable.item5,R.drawable.item6};

        String[] aID={"P001","P002","P003","P004","P005","P006","P007","P008","P009"};
        for(int i=0;i<aimg.length;i++){
            Log.d("Image ID", "Image at index " + i + ": " + String.valueOf(aimg[i]));
                db.Query("update product set img =N'"+String.valueOf(aimg[i])+"' where idp = N'"+aID[i]+"'");
            Log.d("UPDATE thanh cong", String.valueOf(i)+"\n"+aID[i]);
        }
        rcyl.setLayoutManager(new GridLayoutManager(mview.getContext(), 2));
    }

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
}