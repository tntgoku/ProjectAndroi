package com.example.myapplication.FragmentITem;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.Adapter.CartAdapter;
import com.example.myapplication.Controller.IntentKeys;
import com.example.myapplication.Controller.eventSystem;
import com.example.myapplication.R;
import com.example.myapplication.databinding.Fragment1Binding;

public class Fragment_1 extends Fragment implements eventSystem {

    private Fragment1ViewModel mViewModel;

    public static Fragment_1 newInstance() {
        return new Fragment_1();
    }
    RecyclerView rec;
    Fragment1Binding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= Fragment1Binding.inflate(inflater, container, false);

        setID();
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

    }

    @Override
    public void eVentID() {
        rec.setAdapter(new CartAdapter(IntentKeys.listcart));
        rec.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext(), RecyclerView.VERTICAL,false));
    }
}