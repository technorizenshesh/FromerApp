package com.my.fromerapp.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.my.fromerapp.Preference;
import com.my.fromerapp.R;
import com.my.fromerapp.adapter.OrderHistoryAdapter;
import com.my.fromerapp.adapter.SubProductRecyclerViewAdapter;
import com.my.fromerapp.databinding.ActivityOrderHistoryBinding;
import com.my.fromerapp.model.AllSumeryModel;
import com.my.fromerapp.model.HomeModel;
import com.my.fromerapp.model.OrderHistoryModel;
import com.my.fromerapp.model.SummeryDataModel;
import com.my.fromerapp.utils.RetrofitClients;
import com.my.fromerapp.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class OrderHistory extends AppCompatActivity {

    OrderHistoryAdapter mAdapter;
    private ArrayList<OrderHistoryModel.Result> modelList = new ArrayList<>();
    private SessionManager sessionManager;
    ActivityOrderHistoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_order_history);

        binding.RRBack.setOnClickListener(v -> {

            onBackPressed();

        });

        sessionManager = new SessionManager(OrderHistory.this);

        if (sessionManager.isNetworkAvailable()) {

            binding.progressBar.setVisibility(VISIBLE);

            getOrderhistory();

        }else {
            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }
    }


    private void setAdapter(ArrayList<OrderHistoryModel.Result> modelList) {

        mAdapter = new OrderHistoryAdapter(OrderHistory.this,modelList,"order");
        binding.recyclerOrderHistory.setHasFixedSize(true);
        // use a linear layout manager
        //  binding.recyclerSearch.setLayoutManager(new GridLayoutManager(this, 2));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderHistory.this);
        binding.recyclerOrderHistory.setLayoutManager(linearLayoutManager);
        binding.recyclerOrderHistory.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new OrderHistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, OrderHistoryModel.Result model) {

            }
        });

    }


    public void getOrderhistory() {

        String buyer_id = Preference.get(OrderHistory.this,Preference.KEY_user_id);

        Call<OrderHistoryModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_order_history(buyer_id);
        call.enqueue(new Callback<OrderHistoryModel>() {
            @Override
            public void onResponse(Call<OrderHistoryModel> call, Response<OrderHistoryModel> response) {
                try {

                    binding.progressBar.setVisibility(GONE);
                    binding.txtEmty.setVisibility(GONE);

                    OrderHistoryModel myclass = response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")) {

                        modelList = (ArrayList<OrderHistoryModel.Result>) myclass.getResult();
                        setAdapter(modelList);

                    } else {
                        binding.txtEmty.setVisibility(View.VISIBLE);
                        //binding.txtEMPTY.setVisibility(View.VISIBLE);
                        Toast.makeText(OrderHistory.this, result, Toast.LENGTH_SHORT).show();
                    }

                } catch(Exception e) {
                    binding.txtEmty.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<OrderHistoryModel> call, Throwable t) {
                binding.progressBar.setVisibility(GONE);
                binding.txtEmty.setVisibility(View.VISIBLE);
                Toast.makeText(OrderHistory.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}