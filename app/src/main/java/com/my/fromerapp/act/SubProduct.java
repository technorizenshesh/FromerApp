package com.my.fromerapp.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.my.fromerapp.Preference;
import com.my.fromerapp.R;
import com.my.fromerapp.adapter.HomeSaloonRecyclerViewAdapter;
import com.my.fromerapp.adapter.SubProductRecyclerViewAdapter;
import com.my.fromerapp.databinding.ActivitySubProductBinding;
import com.my.fromerapp.model.FrmModel;
import com.my.fromerapp.model.FrmModelData;
import com.my.fromerapp.model.HomeModel;
import com.my.fromerapp.model.SubCategoryModel;
import com.my.fromerapp.model.SubCategoryModelData;
import com.my.fromerapp.utils.RetrofitClients;
import com.my.fromerapp.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubProduct extends AppCompatActivity {

    ActivitySubProductBinding binding;
    SubProductRecyclerViewAdapter mAdapter;
    private ArrayList<FrmModelData> modelList = new ArrayList<>();
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_sub_product);

        String title = Preference.get(SubProduct.this,Preference.KEYsuubcattitle);

        binding.title.setText(title);

        binding.RRBack.setOnClickListener(v -> {

            onBackPressed();

        });

        sessionManager = new SessionManager(SubProduct.this);

        if (sessionManager.isNetworkAvailable()) {

            binding.progressBar.setVisibility(View.VISIBLE);

            getFrmdetails();

        } else {

            Toast.makeText(SubProduct.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }

    }


    private void setAdapter(ArrayList<FrmModelData> modelList) {

        mAdapter = new SubProductRecyclerViewAdapter(SubProduct.this, this.modelList);
        binding.recyclerSearch.setHasFixedSize(true);
        // use a linear layout manager
      //  binding.recyclerSearch.setLayoutManager(new GridLayoutManager(this, 2));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SubProduct.this);
        binding.recyclerSearch.setLayoutManager(linearLayoutManager);
        binding.recyclerSearch.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new SubProductRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, FrmModelData model) {

                Preference.save(SubProduct.this,Preference.KEY_seller_id,model.getSellerId());

                startActivity(new Intent(SubProduct.this, ProductDetails.class));

            }
        });
    }


    public void getFrmdetails() {

        String ccat_Id= Preference.get(SubProduct.this,Preference.KEYcategory_id);
        String sub_cat_Id= Preference.get(SubProduct.this,Preference.KEY_sub_Id);

        Call<FrmModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_firm_details(ccat_Id,sub_cat_Id);
        call.enqueue(new Callback<FrmModel>() {
            @Override
            public void onResponse(Call<FrmModel> call, Response<FrmModel> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);

                    FrmModel myclass = response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")) {

                        binding.txtEMPTY.setVisibility(View.GONE);
                        modelList= (ArrayList<FrmModelData>) myclass.getResult();

                        setAdapter(modelList);

                    } else {

                        binding.txtEMPTY.setVisibility(View.VISIBLE);

                        Toast.makeText(SubProduct.this, result, Toast.LENGTH_SHORT).show();
                    }

                } catch(Exception e) {
                    binding.txtEMPTY.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<FrmModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                binding.txtEMPTY.setVisibility(View.VISIBLE);
                Toast.makeText(SubProduct.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}