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
import com.my.fromerapp.databinding.ActivityCropsBinding;
import com.my.fromerapp.model.HomeModel;
import com.my.fromerapp.model.SubCategoryModel;
import com.my.fromerapp.model.SubCategoryModelData;
import com.my.fromerapp.utils.RetrofitClients;
import com.my.fromerapp.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CropsActivity extends AppCompatActivity {

    ActivityCropsBinding binding;
    HomeSaloonRecyclerViewAdapter mAdapter;
    private ArrayList<SubCategoryModelData> modelList = new ArrayList<>();
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_crops);

       String title = Preference.get(CropsActivity.this,Preference.KEYLivestock);

        binding.title.setText(title);

     binding.RRBack.setOnClickListener(v -> {
            onBackPressed();
        });

        sessionManager = new SessionManager(CropsActivity.this);

        if (sessionManager.isNetworkAvailable()) {

            binding.progressBar.setVisibility(View.VISIBLE);

            getSubCategory();

        } else {

            Toast.makeText(CropsActivity.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }

    }

    private void setAdapter(ArrayList<SubCategoryModelData> modelList) {

        mAdapter = new HomeSaloonRecyclerViewAdapter(CropsActivity.this, this.modelList);
        binding.recyclercrops.setHasFixedSize(true);
        // use a linear layout manager
        binding.recyclercrops.setLayoutManager(new GridLayoutManager(this, 2));
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CropsActivity.this);
        // binding.recyclercrops.setLayoutManager(linearLayoutManager);
        binding.recyclercrops.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new HomeSaloonRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, SubCategoryModelData model) {

                Preference.save(CropsActivity.this,Preference.KEYsuubcattitle,model.getName());
                Preference.save(CropsActivity.this,Preference.KEY_sub_Id,model.getId());

                startActivity(new Intent(CropsActivity.this, SubProduct.class));
            }
        });

    }

    public void getSubCategory() {

        String ccat_Id= Preference.get(CropsActivity.this,Preference.KEYcategory_id);

        Call<SubCategoryModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_subcategory(ccat_Id);
        call.enqueue(new Callback<SubCategoryModel>() {
            @Override
            public void onResponse(Call<SubCategoryModel> call, Response<SubCategoryModel> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);

                    SubCategoryModel myclass = response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")) {

                        modelList = (ArrayList<SubCategoryModelData>) myclass.getResult();

                         setAdapter(modelList);

                    } else {
                        Toast.makeText(CropsActivity.this, result, Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<SubCategoryModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(CropsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}