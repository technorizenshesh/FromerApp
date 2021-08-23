package com.my.fromerapp.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.my.fromerapp.R;
import com.my.fromerapp.adapter.OrderHistoryAdapter;
import com.my.fromerapp.adapter.SubProductRecyclerViewAdapter;
import com.my.fromerapp.databinding.ActivityOrderHistoryBinding;
import com.my.fromerapp.model.HomeModel;

import java.util.ArrayList;

public class OrderHistory extends AppCompatActivity {

    ActivityOrderHistoryBinding binding;
    OrderHistoryAdapter mAdapter;
    private ArrayList<HomeModel> modelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_order_history);

        binding.RRBack.setOnClickListener(v -> {

            onBackPressed();

        });

        setAdapter();
    }


    private void setAdapter() {

        this.modelList.add(new HomeModel("Corn"));
        this.modelList.add(new HomeModel("Tomotoes"));
        this.modelList.add(new HomeModel("Cassava"));
        this.modelList.add(new HomeModel("Rice"));
        this.modelList.add(new HomeModel("Soyabeans"));
        this.modelList.add(new HomeModel("Cocoa"));

        mAdapter = new OrderHistoryAdapter(OrderHistory.this,modelList);
        binding.recyclerOrderHistory.setHasFixedSize(true);
        // use a linear layout manager
        //  binding.recyclerSearch.setLayoutManager(new GridLayoutManager(this, 2));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderHistory.this);
        binding.recyclerOrderHistory.setLayoutManager(linearLayoutManager);
        binding.recyclerOrderHistory.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new OrderHistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, HomeModel model) {

            }
        });
    }
}