package com.example.myapplication.FragmentITem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class selectiembuy extends Fragment {
    private View mview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout cho Fragment
        mview = inflater.inflate(R.layout.fragment_1, container, false);

        // Tìm View bằng ID

        return mview;
    }
}
