package com.kjsc.myapplication.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.kjsc.myapplication.databinding.ActivitySecondBinding;
import com.kjsc.myapplication.databinding.ActivityThirdBinding;

public class ThirdActivity extends AppCompatActivity {
    private ActivityThirdBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThirdBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
    }
}
