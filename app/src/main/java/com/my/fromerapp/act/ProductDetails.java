package com.my.fromerapp.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.my.fromerapp.MainActivity;
import com.my.fromerapp.Preference;
import com.my.fromerapp.R;
import com.my.fromerapp.adapter.MyOrderRecyclerViewAdapter;
import com.my.fromerapp.adapter.ProducctRecyclerViewAdapter;
import com.my.fromerapp.databinding.ActivityProductDetailsBinding;
import com.my.fromerapp.fragment.BottomAddToCardFragment;
import com.my.fromerapp.interfacesss.additemListener;
import com.my.fromerapp.model.AddToCardModel;
import com.my.fromerapp.model.FrmModel;
import com.my.fromerapp.model.FrmModelData;
import com.my.fromerapp.model.HomeModel;
import com.my.fromerapp.model.LoginModel;
import com.my.fromerapp.model.ProductDetailsMoodel;
import com.my.fromerapp.model.ProductmySellerModelData;
import com.my.fromerapp.utils.RetrofitClients;
import com.my.fromerapp.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetails extends AppCompatActivity implements additemListener {

    ActivityProductDetailsBinding binding;
    ProducctRecyclerViewAdapter mAdapter;
    private ArrayList<ProductmySellerModelData> modelList = new ArrayList<>();
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_product_details);

        sessionManager = new SessionManager(ProductDetails.this);

        if (sessionManager.isNetworkAvailable()) {

            binding.progressBar.setVisibility(View.VISIBLE);

            getFrmdetailsProductseller();
        }else {

            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();

        }
    }

    private void setAdapter(ArrayList<ProductmySellerModelData> modelList) {

        mAdapter = new ProducctRecyclerViewAdapter(ProductDetails.this, this.modelList,ProductDetails.this);
        binding.recyclerAllproduct.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProductDetails.this);
        binding.recyclerAllproduct.setLayoutManager(linearLayoutManager);
        binding.recyclerAllproduct.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new ProducctRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ProductmySellerModelData model) {

            }
        });
    }

    public void getFrmdetailsProductseller() {

        String Seller_id = Preference.get(ProductDetails.this,Preference.KEY_seller_id);
       String buyer_id = Preference.get(ProductDetails.this,Preference.KEY_user_id);

        Call<ProductDetailsMoodel> call = RetrofitClients
                .getInstance()
                .getApi()
                .firm_details_byID(Seller_id,buyer_id);
        call.enqueue(new Callback<ProductDetailsMoodel>() {
            @Override
            public void onResponse(Call<ProductDetailsMoodel> call, Response<ProductDetailsMoodel> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);

                    ProductDetailsMoodel myclass = response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")) {

                        Glide.with(ProductDetails.this).load(myclass.getResult().getFirmImage()).placeholder(R.mipmap.details).into(binding.img1);

                        binding.txtName.setText(myclass.getResult().getFirmName());
                        binding.txtAddress.setText(myclass.getResult().getFirmAddress());
                        binding.txtabout.setText(myclass.getResult().getAbout());

                        modelList= (ArrayList<ProductmySellerModelData>) myclass.getProduct();

                        setAdapter(modelList);

                    } else {

                        binding.txtEMPTY.setVisibility(View.VISIBLE);

                        Toast.makeText(ProductDetails.this, result, Toast.LENGTH_SHORT).show();
                    }

                } catch(Exception e) {
                    binding.txtEMPTY.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ProductDetailsMoodel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                binding.txtEMPTY.setVisibility(View.VISIBLE);
                Toast.makeText(ProductDetails.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void add_to_cart_buyer(String ProductId,String quntity){

        String Seller_id = Preference.get(ProductDetails.this,Preference.KEY_seller_id);
        String buyer_id = Preference.get(ProductDetails.this,Preference.KEY_user_id);


        Call<AddToCardModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .add_to_cart_buyer(Seller_id,buyer_id,ProductId,quntity);

        call.enqueue(new Callback<AddToCardModel>() {
            @Override
            public void onResponse(Call<AddToCardModel> call, Response<AddToCardModel> response) {

                binding.progressBar.setVisibility(View.GONE);

                AddToCardModel finallyPr = response.body();

                String status = finallyPr.getStatus();

                if (status.equalsIgnoreCase("1")) {

                    Toast.makeText(ProductDetails.this, finallyPr.getResult(), Toast.LENGTH_SHORT).show();

                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(ProductDetails.this, finallyPr.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AddToCardModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(ProductDetails.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void addItem(int pos) {

        BottomAddToCardFragment bottomSheetFragment= new BottomAddToCardFragment(modelList.get(pos).getId(),modelList.get(pos).getName(),modelList.get(pos).getPrice(),modelList.get(pos).getImage(),ProductDetails.this);
        bottomSheetFragment.show(getSupportFragmentManager(),"ModalBottomSheet");

    }

    @Override
    public void addItem(int pos, int quntity) {

        if (sessionManager.isNetworkAvailable()) {
            binding.progressBar.setVisibility(View.VISIBLE);
            add_to_cart_buyer(modelList.get(pos).getId(), String.valueOf(quntity));

        }else {
            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }
    }

}