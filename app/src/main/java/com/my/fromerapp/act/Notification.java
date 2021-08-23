package com.my.fromerapp.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.my.fromerapp.R;
import com.my.fromerapp.adapter.NotificationAdapter;
import com.my.fromerapp.adapter.SubProductRecyclerViewAdapter;
import com.my.fromerapp.databinding.ActivityNotificationBinding;
import com.my.fromerapp.model.HomeModel;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {

    ActivityNotificationBinding binding;
    NotificationAdapter mAdapter;
    private ArrayList<HomeModel> modelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_notification);

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

        mAdapter = new NotificationAdapter(Notification.this,modelList);
        binding.recyclerNotification.setHasFixedSize(true);
        // use a linear layout manager
        //  binding.recyclerSearch.setLayoutManager(new GridLayoutManager(this, 2));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Notification.this);
        binding.recyclerNotification.setLayoutManager(linearLayoutManager);
        binding.recyclerNotification.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new NotificationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, HomeModel model) {

            }
        });
    }
}