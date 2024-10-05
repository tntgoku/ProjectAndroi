package com.example.myapplication.FragmentITem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.CartAdapter;
import com.example.myapplication.Controller.IntentKeys;
import com.example.myapplication.Controller.eventSystem;
import com.example.myapplication.databinding.Fragment1Binding;

import vn.zalopay.sdk.ZaloPaySDK;
public class Fragment_1 extends Fragment implements eventSystem {

    private Fragment1ViewModel mViewModel;

    public static Fragment_1 newInstance() {
        return new Fragment_1();
    }
    RecyclerView rec;
    private static TextView a;
    private TextView a1;
    private TextView a2;
    private TextView a3;
    private TextView a4;
    private TextView a5;
    private TextView a6;
    Fragment1Binding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= Fragment1Binding.inflate(inflater, container, false);

        setID();
        if(IntentKeys.listcart.size()!=0){
        for(int i=0;i<IntentKeys.listcart.size();i++){
            Log.d("Producttt", IntentKeys.listcart.get(i).getIDP()+"\n"+IntentKeys.listcart.get(i).getQuantity()+"\n"+IntentKeys.listcart.get(i).getCost());
        }

        }
        eVentID();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Fragment1ViewModel.class);

        // TODO: Use the ViewModel
    }

    @Override
    public void setID() {
        rec=binding.recyclecart;
        a=binding.totalbill;
        a1=binding.buybtn1;
        a2=binding.updatecart1;
    }

    @Override
    public void eVentID() {

        CartAdapter as =new CartAdapter(IntentKeys.listcart);
        rec.setAdapter(as);
        rec.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext(), RecyclerView.VERTICAL,false));

        updatetotal();


        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                as.toggleI3Visibility();
                as.notifyDataSetChanged();
            }
        });
    }

    public static void updatetotal(){
        a.setText(IntentKeys.getTotalBillCart()+" đ");
    }

    private void checkBox(){

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}