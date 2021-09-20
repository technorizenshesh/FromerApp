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
import com.my.fromerapp.adapter.GetShippingAddressAdapter;
import com.my.fromerapp.adapter.MyGetcardItemAdapter;
import com.my.fromerapp.databinding.ActivityShipppingAddressBinding;
import com.my.fromerapp.interfacesss.DeleteAdressListener;
import com.my.fromerapp.model.AddToCardModel;
import com.my.fromerapp.model.GetShippingAddress;
import com.my.fromerapp.model.GetShippingAddressData;
import com.my.fromerapp.model.GteItemProductModel;
import com.my.fromerapp.model.GteItemProductModelData;
import com.my.fromerapp.model.SelectedAddreessModel;
import com.my.fromerapp.utils.RetrofitClients;
import com.my.fromerapp.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ShipppingAddress extends AppCompatActivity implements DeleteAdressListener {

    ActivityShipppingAddressBinding binding;
    GetShippingAddressAdapter mAdapter;
    private ArrayList<GetShippingAddressData> modelList = new ArrayList<>();
    private SessionManager sessionManager;

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

     binding.txtNext.setOnClickListener(v -> {
        // startActivity(new Intent(this, GtePaymentCartActivity.class));
         startActivity(new Intent(this, SummeryActiivty.class));

     });

        sessionManager = new SessionManager(ShipppingAddress.this);

        if (sessionManager.isNetworkAvailable()) {
            binding.progressBar.setVisibility(VISIBLE);
            getShppingAddress();

        }else {
            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }

    }

    private void setAdapter(ArrayList<GetShippingAddressData> modelList) {

      /*  this.modelList.add(new HomeModel("Corn"));
        this.modelList.add(new HomeModel("Tomotoes"));
        this.modelList.add(new HomeModel("Cassava"));*/

        mAdapter = new GetShippingAddressAdapter(ShipppingAddress.this, modelList,ShipppingAddress.this);
        binding.recyclerShiipingAddrewss.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShipppingAddress.this);
        binding.recyclerShiipingAddrewss.setLayoutManager(linearLayoutManager);
        binding.recyclerShiipingAddrewss.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new GetShippingAddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, GetShippingAddressData model) {

                binding.progressBar.setVisibility(VISIBLE);
                getSelectedAddress(model.getId());

            }
        });


    }

    public void getShppingAddress() {

        String buyer_id = Preference.get(ShipppingAddress.this,Preference.KEY_user_id);
        //String buyer_id="2";

        Call<GetShippingAddress> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_shipping_address(buyer_id);
        call.enqueue(new Callback<GetShippingAddress>() {
            @Override
            public void onResponse(Call<GetShippingAddress> call, Response<GetShippingAddress> response) {
                try {

                    binding.progressBar.setVisibility(GONE);

                    GetShippingAddress myclass = response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")) {
                        binding.txtEmty.setVisibility(GONE);
                        binding.txtNext.setVisibility(VISIBLE);
                        binding.LLCard.setVisibility(VISIBLE);
                        modelList= (ArrayList<GetShippingAddressData>) myclass.getResult();

                        setAdapter(modelList);

                    } else {
                        binding.LLCard.setVisibility(GONE);
                        binding.txtNext.setVisibility(GONE);
                        binding.txtEmty.setVisibility(VISIBLE);
                        //binding.txtEMPTY.setVisibility(View.VISIBLE);
                        Toast.makeText(ShipppingAddress.this, result, Toast.LENGTH_SHORT).show();
                    }

                } catch(Exception e) {
                    binding.LLCard.setVisibility(GONE);
                    binding.txtNext.setVisibility(GONE);
                    binding.txtEmty.setVisibility(VISIBLE);
                    //    binding.txtEMPTY.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<GetShippingAddress> call, Throwable t) {
                binding.progressBar.setVisibility(GONE);
                binding.txtEmty.setVisibility(VISIBLE);
                binding.txtNext.setVisibility(GONE);
                binding.LLCard.setVisibility(GONE);
                //  binding.txtEMPTY.setVisibility(View.VISIBLE);
                Toast.makeText(ShipppingAddress.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getSelectedAddress(String id) {

        String buyer_id = Preference.get(ShipppingAddress.this,Preference.KEY_user_id);

        Call<SelectedAddreessModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .update_is_select(buyer_id,id);
        call.enqueue(new Callback<SelectedAddreessModel>() {
            @Override
            public void onResponse(Call<SelectedAddreessModel> call, Response<SelectedAddreessModel> response) {
                try {

                    binding.progressBar.setVisibility(GONE);
                    SelectedAddreessModel myclass = response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")) {

                        Toast.makeText(ShipppingAddress.this, myclass.getResult(), Toast.LENGTH_SHORT).show();

                        binding.progressBar.setVisibility(VISIBLE);
                        getShppingAddress();

                    } else {

                        Toast.makeText(ShipppingAddress.this, result, Toast.LENGTH_SHORT).show();
                    }

                } catch(Exception e) {
                    //    binding.txtEMPTY.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<SelectedAddreessModel> call, Throwable t) {
                binding.progressBar.setVisibility(GONE);
                Toast.makeText(ShipppingAddress.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void delete_hip_address(String id){

        Call<AddToCardModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .remove_ship_address(id);

        call.enqueue(new Callback<AddToCardModel>() {
            @Override
            public void onResponse(Call<AddToCardModel> call, Response<AddToCardModel> response) {

                binding.progressBar.setVisibility(View.GONE);

                AddToCardModel finallyPr = response.body();

                String status = finallyPr.getStatus();

                if (status.equalsIgnoreCase("1")) {

                    binding.progressBar.setVisibility(VISIBLE);

                    getShppingAddress();

                    Toast.makeText(ShipppingAddress.this, finallyPr.getResult(), Toast.LENGTH_SHORT).show();

                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(ShipppingAddress.this, finallyPr.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AddToCardModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(ShipppingAddress.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void deleteItem(int pos) {

        String Id= modelList.get(pos).getId();

        if(pos !=0){

            modelList.remove(pos);
            delete_hip_address( modelList.get(pos).getId());

        }else
        {
            binding.txtEmty.setVisibility(VISIBLE);
            binding.txtNext.setVisibility(GONE);
            binding.LLCard.setVisibility(GONE);
            binding.progressBar.setVisibility(VISIBLE);
            delete_hip_address(Id);
        }
    }
}