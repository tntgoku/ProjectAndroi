package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
    }

    void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.layoutfragment,fragment).commit();
    }
}