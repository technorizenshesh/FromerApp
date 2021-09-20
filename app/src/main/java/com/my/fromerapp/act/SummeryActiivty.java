package com.my.fromerapp.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.my.fromerapp.MainActivity;
import com.my.fromerapp.Preference;
import com.my.fromerapp.R;
import com.my.fromerapp.adapter.GETAllItemGetcardItemAdapter;
import com.my.fromerapp.adapter.MyGetcardItemAdapter;
import com.my.fromerapp.databinding.ActivitySummeryActiivtyBinding;
import com.my.fromerapp.model.AllSumeryModel;
import com.my.fromerapp.model.GteItemProductModel;
import com.my.fromerapp.model.GteItemProductModelData;
import com.my.fromerapp.model.SummeryDataModel;
import com.my.fromerapp.utils.RetrofitClients;
import com.my.fromerapp.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class SummeryActiivty extends AppCompatActivity {

    ActivitySummeryActiivtyBinding binding;
    GETAllItemGetcardItemAdapter mAdapter;
    private ArrayList<SummeryDataModel> modelList = new ArrayList<>();
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_summery_actiivty);

        binding.AddSummery.setOnClickListener(v -> {
          //  binding.progressBar.setVisibility(View.VISIBLE);
          Intent intent = new Intent(SummeryActiivty.this, MainActivity.class);
            startActivity(intent);
            finish();

        });

        sessionManager = new SessionManager(SummeryActiivty.this);

        if (sessionManager.isNetworkAvailable()) {

            binding.progressBar.setVisibility(VISIBLE);

            getAllSuMmeryItem();

        }else {
            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }
    }

    private void setAdapter(ArrayList<SummeryDataModel> modelList) {

        mAdapter = new GETAllItemGetcardItemAdapter(SummeryActiivty.this, modelList);
        binding.recyclerProduct.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SummeryActiivty.this);
        binding.recyclerProduct.setLayoutManager(linearLayoutManager);
        binding.recyclerProduct.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new GETAllItemGetcardItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, SummeryDataModel model) {

            }
        });
    }

    public void getAllSuMmeryItem() {

        String buyer_id = Preference.get(SummeryActiivty.this,Preference.KEY_user_id);

        Call<AllSumeryModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_sumary(buyer_id);
        call.enqueue(new Callback<AllSumeryModel>() {
            @Override
            public void onResponse(Call<AllSumeryModel> call, Response<AllSumeryModel> response) {
                try {

                    binding.progressBar.setVisibility(GONE);

                    AllSumeryModel myclass = response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")) {

                        String AddressType=myclass.getSelectedAddress().getAddressType();
                        String Address1=myclass.getSelectedAddress().getStreetAddress1();
                        String Address2=myclass.getSelectedAddress().getStreetAddress2();
                        String city=myclass.getSelectedAddress().getCity();
                        String state=myclass.getSelectedAddress().getState();
                        String Country=myclass.getSelectedAddress().getCountry();

                        if(AddressType!=null)
                        {
                            binding.txtAddressType.setText(AddressType+"");
                        }

                        if(Address1!=null || Address2!=null || city!=null || state!=null || Country!=null)
                        {
                            binding.txtAddress.setText(Address1 +","+Address2+","+city+","+state+","+Country);
                        }

                        if(myclass.getResult()!=null)
                        {
                            modelList= (ArrayList<SummeryDataModel>) myclass.getResult();
                            setAdapter(modelList);
                        }

                    } else {
                        //binding.txtEMPTY.setVisibility(View.VISIBLE);
                        Toast.makeText(SummeryActiivty.this, result, Toast.LENGTH_SHORT).show();
                    }

                } catch(Exception e) {
                    //    binding.txtEMPTY.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<AllSumeryModel> call, Throwable t) {
                binding.progressBar.setVisibility(GONE);
                //  binding.txtEMPTY.setVisibility(View.VISIBLE);
                Toast.makeText(SummeryActiivty.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}