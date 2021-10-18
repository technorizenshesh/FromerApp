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
import com.my.fromerapp.adapter.MyGetcardItemAdapter;
import com.my.fromerapp.databinding.ActivityGetCartItemsBinding;
import com.my.fromerapp.interfacesss.CarditemListener;
import com.my.fromerapp.model.AddToCardModel;
import com.my.fromerapp.model.AddWishModel;
import com.my.fromerapp.model.GteItemProductModel;
import com.my.fromerapp.model.GteItemProductModelData;
import com.my.fromerapp.utils.RetrofitClients;
import com.my.fromerapp.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class GetCartItemsActivity extends AppCompatActivity implements CarditemListener {

    ActivityGetCartItemsBinding binding;
    MyGetcardItemAdapter mAdapter;
    private ArrayList<GteItemProductModelData> modelList = new ArrayList<>();
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_get_cart_items);



        binding.txtCheckOut.setOnClickListener(v -> {

            if(modelList.isEmpty() || modelList !=null)
            {
                startActivity(new Intent(GetCartItemsActivity.this, ShipppingAddress.class));

            }else
            {
                Toast.makeText(this, "Please add Item.", Toast.LENGTH_SHORT).show();
            }


        });
        binding.RRBack.setOnClickListener(v -> {

           onBackPressed();
        });

        binding.txtApply.setOnClickListener(v -> {
            if(!binding.edtCoupan.getText().toString().equalsIgnoreCase(""))
            {
                binding.txtCopan.setVisibility(VISIBLE);
                binding.edtCoupan.setText("");

            }else
            {
                Toast.makeText(this, "Please Enter Copan Code", Toast.LENGTH_SHORT).show();
            }

        });

        sessionManager = new SessionManager(GetCartItemsActivity.this);


        if (sessionManager.isNetworkAvailable()) {

            binding.progressBar.setVisibility(VISIBLE);

            getProductCardItem();

        }else {
            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }
    }

    private void setAdapter(ArrayList<GteItemProductModelData> modelList) {

      /*  this.modelList.add(new HomeModel("Corn"));
        this.modelList.add(new HomeModel("Tomotoes"));
        this.modelList.add(new HomeModel("Cassava"));*/

        mAdapter = new MyGetcardItemAdapter(GetCartItemsActivity.this, modelList,GetCartItemsActivity.this);
        binding.recycler.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GetCartItemsActivity.this);
        binding.recycler.setLayoutManager(linearLayoutManager);
        binding.recycler.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new MyGetcardItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, GteItemProductModelData model) {

            }
        });
    }

    public void getProductCardItem() {

        String buyer_id = Preference.get(GetCartItemsActivity.this,Preference.KEY_user_id);

        Call<GteItemProductModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_card_buyer(buyer_id);
        call.enqueue(new Callback<GteItemProductModel>() {
            @Override
            public void onResponse(Call<GteItemProductModel> call, Response<GteItemProductModel> response) {
                try {

                    binding.progressBar.setVisibility(GONE);

                    GteItemProductModel myclass = response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")) {
                        binding.LLCard.setVisibility(VISIBLE);
                        binding.llItem.setVisibility(VISIBLE);
                        binding.txtCheckOut.setVisibility(VISIBLE);
                        binding.txtEmty.setVisibility(GONE);

                        binding.txtTotal.setText("Rs."+myclass.getTotalAmount().toString());
                        binding.txtTotalOne.setText("Rs."+myclass.getTotalAmount().toString());

                        modelList= (ArrayList<GteItemProductModelData>) myclass.getResult();

                        setAdapter(modelList);

                    } else {
                        //binding.txtEMPTY.setVisibility(View.VISIBLE);
                        binding.LLCard.setVisibility(GONE);
                        binding.llItem.setVisibility(GONE);
                        binding.txtEmty.setVisibility(VISIBLE);
                        Toast.makeText(GetCartItemsActivity.this, result, Toast.LENGTH_SHORT).show();
                    }

                } catch(Exception e) {
                    binding.llItem.setVisibility(GONE);
                    binding.LLCard.setVisibility(GONE);
                    binding.txtCheckOut.setVisibility(GONE);
                    binding.txtEmty.setVisibility(VISIBLE);
                //    binding.txtEMPTY.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<GteItemProductModel> call, Throwable t) {
                binding.progressBar.setVisibility(GONE);
                binding.llItem.setVisibility(GONE);
                binding.LLCard.setVerticalGravity(GONE);
                binding.txtCheckOut.setVisibility(GONE);
                binding.txtEmty.setVisibility(VISIBLE);

              }
        });
    }


    private void delete_card(String CardId){

        Call<AddToCardModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .delete_card(CardId);

        call.enqueue(new Callback<AddToCardModel>() {
            @Override
            public void onResponse(Call<AddToCardModel> call, Response<AddToCardModel> response) {

                binding.progressBar.setVisibility(View.GONE);

                AddToCardModel finallyPr = response.body();

                String status = finallyPr.getStatus();

                if (status.equalsIgnoreCase("1")) {

                    binding.progressBar.setVisibility(VISIBLE);

                    getProductCardItem();

                    Toast.makeText(GetCartItemsActivity.this, finallyPr.getResult(), Toast.LENGTH_SHORT).show();

                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(GetCartItemsActivity.this, finallyPr.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AddToCardModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(GetCartItemsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void add_wish(String ProductId){

        String buyer_id = Preference.get(GetCartItemsActivity.this,Preference.KEY_user_id);


        Call<AddWishModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .addwish(buyer_id,ProductId);
        call.enqueue(new Callback<AddWishModel>() {
            @Override
            public void onResponse(Call<AddWishModel> call, Response<AddWishModel> response) {

                binding.progressBar.setVisibility(View.GONE);

                AddWishModel finallyPr = response.body();

                String status = finallyPr.getStatus();

                if (status.equalsIgnoreCase("1")) {

                    Toast.makeText(GetCartItemsActivity.this, finallyPr.getMessage(), Toast.LENGTH_SHORT).show();

                    binding.progressBar.setVisibility(View.VISIBLE);

                    getProductCardItem();

                } else {

                    binding.progressBar.setVisibility(View.GONE);

                    Toast.makeText(GetCartItemsActivity.this, finallyPr.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AddWishModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(GetCartItemsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void cardItem(int pos) {

       String Id= modelList.get(pos).getId();

        if(pos !=0){

            modelList.remove(pos);
            delete_card( modelList.get(pos).getId());

        }else
        {
            binding.LLCard.setVisibility(GONE);
            binding.llItem.setVisibility(GONE);
            binding.txtCheckOut.setVisibility(GONE);
            binding.txtEmty.setVisibility(VISIBLE);

            Toast.makeText(this, "remove this item", Toast.LENGTH_SHORT).show();

            delete_card(Id);
        }

    }

    @Override
    public void WishItem(int pos) {

        String Id= modelList.get(pos).getProductId();

        if(pos!=0)
        {
            if (sessionManager.isNetworkAvailable()) {

                binding.progressBar.setVisibility(View.VISIBLE);

                modelList.remove(pos);

                add_wish(Id);

            }else {

                Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
            }

        }else
        {

            add_wish(Id);

        }

    }

}