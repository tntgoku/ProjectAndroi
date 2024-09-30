package com.example.myapplication.Adapter;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityImageAdapterBinding;

public class ImageAdapter extends AppCompatActivity {
        ActivityImageAdapterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding= ActivityImageAdapterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}