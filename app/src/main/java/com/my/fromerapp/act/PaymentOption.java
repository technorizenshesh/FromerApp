package com.my.fromerapp.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.my.fromerapp.R;
import com.my.fromerapp.databinding.ActivityPaymentOptionBinding;

public class PaymentOption extends AppCompatActivity {

    ActivityPaymentOptionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_payment_option);

        binding.txtProcced.setOnClickListener(v -> {

        });

    }
}