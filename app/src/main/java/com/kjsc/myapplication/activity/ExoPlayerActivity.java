package com.kjsc.myapplication.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kjsc.myapplication.databinding.ActivityExoBinding;

public class ExoPlayerActivity extends AppCompatActivity {
    private ActivityExoBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExoBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
    }
}
