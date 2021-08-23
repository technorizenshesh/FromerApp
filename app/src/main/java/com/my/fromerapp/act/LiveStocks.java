package com.my.fromerapp.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.my.fromerapp.R;
import com.my.fromerapp.adapter.HomeSaloonRecyclerViewAdapter;
import com.my.fromerapp.databinding.ActivityLiveStocksBinding;
import com.my.fromerapp.model.HomeModel;
import com.my.fromerapp.model.SubCategoryModelData;

import java.util.ArrayList;

public class LiveStocks extends AppCompatActivity {

    ActivityLiveStocksBinding binding;
    HomeSaloonRecyclerViewAdapter mAdapter;
    private ArrayList<HomeModel> modelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_live_stocks);

        binding.RRBack.setOnClickListener(v -> {
            onBackPressed();
        });

       // setAdapter();
    }

   /* private void setAdapter() {

        this.modelList.add(new HomeModel("Poutry"));
        this.modelList.add(new HomeModel("Snail"));
        this.modelList.add(new HomeModel("Pig"));
        this.modelList.add(new HomeModel("Fish"));
        this.modelList.add(new HomeModel("OtherLivestock"));

        mAdapter = new HomeSaloonRecyclerViewAdapter(LiveStocks.this,modelList);
        binding.recyclerLivestocks.setHasFixedSize(true);
        // use a linear layout manager
        binding.recyclerLivestocks.setLayoutManager(new GridLayoutManager(this, 2));
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CropsActivity.this);
        // binding.recyclercrops.setLayoutManager(linearLayoutManager);
        binding.recyclerLivestocks.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new HomeSaloonRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, SubCategoryModelData model) {

                startActivity(new Intent(LiveStocks.this, SubProduct.class));
            }
        });*/
}