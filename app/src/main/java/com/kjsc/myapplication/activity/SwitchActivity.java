package com.kjsc.myapplication.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.kjsc.myapplication.databinding.ActivityMainBinding;
import com.kjsc.myapplication.databinding.ActivitySecondBinding;
import com.kjsc.myapplication.databinding.ActivitySwitchBinding;

public class SwitchActivity extends AppCompatActivity {

    private ActivitySwitchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySwitchBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.tvMarquee.setSelected(true);
    }
}
