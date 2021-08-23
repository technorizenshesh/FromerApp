package com.my.fromerapp.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.my.fromerapp.MainActivity;
import com.my.fromerapp.R;
import com.my.fromerapp.databinding.ActivityShipppingAddressBinding;

public class ShipppingAddress extends AppCompatActivity {

    ActivityShipppingAddressBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_shippping_address);

     binding.RRBack.setOnClickListener(v -> {
         onBackPressed();
     });

     binding.RRadd.setOnClickListener(v -> {
         startActivity(new Intent(this, AddShippingAddress.class));

     });
    }
}