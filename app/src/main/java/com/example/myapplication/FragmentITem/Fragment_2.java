package com.example.myapplication.FragmentITem;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.Fragment2Binding;

public class Fragment_2 extends Fragment {

    private Fragment2ViewModel mViewModel;
    private Fragment2Binding binding;
    public static Fragment_2 newInstance() {
        return new Fragment_2();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= Fragment2Binding.inflate(inflater, container, false);


        return  binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Fragment2ViewModel.class);
        // TODO: Use the ViewModel
    }

}