package com.example.myapplication;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.FragmentITem.Fragment_1;
import com.example.myapplication.FragmentITem.Fragment_2;
import com.example.myapplication.FragmentITem.Fragment_me;
import com.example.myapplication.FragmentITem.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String AUTHORITY = "com.example.myapplication.Content";
    private static final String PATH = "products";
    public static final Uri CONTENT_URI = Uri.parse("content://com.example.myapplication.Content/products");
BottomNavigationView btngg;
FrameLayout frl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu_home);
        setID();
        eVentID();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.layoutfragment, new HomeActivity()).commit();
        }

    }

    void setID() {
        btngg = findViewById(R.id.btng);
        frl=findViewById(R.id.layoutfragment);
    }
    void eVentID(){
        btngg.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.viewhome) {
                    loadFragment(new HomeActivity());
                }
                if (id == R.id.viewnotice){
                    loadFragment(new Fragment_2());
                }
                 if (id == R.id.viewcart) {
                    loadFragment(new Fragment_1());
                }
                if (id == R.id.viewme) {
                    loadFragment(new Fragment_me());
                }
                return true;
            }
        });
        loadFragment(new HomeActivity());
//        Cursor cr= getContentResolver().query(CONTENT_URI,null,null,null,null,null);
//        if (cr != null) {
//            while (cr.moveToNext()) {
//                String a, a1, a2;
//                a = cr.getString(0);
//                a1 = cr.getString(1);
//                a2 = cr.getString(2);
//                Log.d("TAGContentProvide", "ID: " + a + "\nName: " + a1 + "\nDesc: " + a2);
//            }
//        }
//        else {
//            Log.e("MainActivity", "Cursor is null");
//        }


    }

    void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.layoutfragment,fragment).commit();
    }
}