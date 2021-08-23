package com.my.fromerapp.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.my.fromerapp.R;
import com.my.fromerapp.databinding.ActivityVerificationBinding;

public class VerificationActivity extends AppCompatActivity {

    ActivityVerificationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_verification);

    }
}