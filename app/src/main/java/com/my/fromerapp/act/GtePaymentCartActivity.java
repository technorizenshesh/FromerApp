package com.my.fromerapp.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.my.fromerapp.R;
import com.my.fromerapp.databinding.ActivityGetCartItemsBinding;
import com.my.fromerapp.databinding.ActivityGtePaymentCartBinding;

public class GtePaymentCartActivity extends AppCompatActivity {

    ActivityGtePaymentCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_gte_payment_cart);

        binding.RRadd.setOnClickListener(v -> {

            startActivity(new Intent(this, AddPaymentCart.class));

        });

    }
}