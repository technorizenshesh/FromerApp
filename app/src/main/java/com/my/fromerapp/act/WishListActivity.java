package com.my.fromerapp.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.my.fromerapp.Preference;
import com.my.fromerapp.R;
import com.my.fromerapp.adapter.MyGetcardItemAdapter;
import com.my.fromerapp.adapter.MyWishlistAdapter;
import com.my.fromerapp.databinding.ActivityWishListBinding;
import com.my.fromerapp.interfacesss.WishlistListener;
import com.my.fromerapp.model.AddToCardModel;
import com.my.fromerapp.model.GteItemProductModel;
import com.my.fromerapp.model.GteItemProductModelData;
import com.my.fromerapp.model.WishModel;
import com.my.fromerapp.model.WishModelData;
import com.my.fromerapp.utils.RetrofitClients;
import com.my.fromerapp.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class WishListActivity extends AppCompatActivity implements WishlistListener {

    ActivityWishListBinding binding;
    MyWishlistAdapter mAdapter;
    private ArrayList<WishModelData> modelList = new ArrayList<>();
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_wish_list);

        sessionManager = new SessionManager(WishListActivity.this);

        binding.RRBack.setOnClickListener(v -> {

            onBackPressed();

        });

        if (sessionManager.isNetworkAvailable()) {
            binding.progressBar.setVisibility(VISIBLE);
            getWishList();

        }else {

            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();

        }

    }

    private void setAdapter(ArrayList<WishModelData> modelList) {
       /*this.modelList.add(new WishModelData("Corn"));
        this.modelList.add(new WishModelData("Tomotoes"));
        this.modelList.add(new WishModelData("Cassava"));
*/
        mAdapter = new MyWishlistAdapter(WishListActivity.this,modelList,WishListActivity.this);
        binding.recyclerWishlist.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WishListActivity.this);
        binding.recyclerWishlist.setLayoutManager(linearLayoutManager);
        binding.recyclerWishlist.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new MyWishlistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, WishModelData model) {

            }
        });

    }

    public void getWishList() {

        String buyer_id = Preference.get(WishListActivity.this,Preference.KEY_user_id);

        Call<WishModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_wishList(buyer_id);
        call.enqueue(new Callback<WishModel>() {
            @Override
            public void onResponse(Call<WishModel> call, Response<WishModel> response) {
                try {

                    binding.progressBar.setVisibility(GONE);


                    WishModel myclass = response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")) {

                        binding.txtEmty.setVisibility(GONE);

                        modelList= (ArrayList<WishModelData>) myclass.getResult();

                        setAdapter(modelList);

                    } else {
                        binding.txtEmty.setVisibility(VISIBLE);
                    }

                } catch(Exception e) {
                    e.printStackTrace();
                    binding.txtEmty.setVisibility(VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<WishModel> call, Throwable t) {

                binding.progressBar.setVisibility(GONE);
                binding.txtEmty.setVisibility(VISIBLE);
                Toast.makeText(WishListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void add_to_cart_buyer(String SellerId,String ProductId,String quntity){

        String buyer_id = Preference.get(WishListActivity.this,Preference.KEY_user_id);

        Call<AddToCardModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .add_card_wishList(ProductId,buyer_id,SellerId,"1");

        call.enqueue(new Callback<AddToCardModel>() {
            @Override
            public void onResponse(Call<AddToCardModel> call, Response<AddToCardModel> response) {

                binding.progressBar.setVisibility(View.GONE);

                AddToCardModel finallyPr = response.body();

                String status = finallyPr.getStatus();

                if (status.equalsIgnoreCase("1")) {

                    Toast.makeText(WishListActivity.this, finallyPr.getResult(), Toast.LENGTH_SHORT).show();

                    binding.progressBar.setVisibility(View.VISIBLE);

                    getWishList();

                } else {

                    binding.progressBar.setVisibility(View.GONE);

                    Toast.makeText(WishListActivity.this, finallyPr.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AddToCardModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(WishListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void Wishlist(int pos) {

        if(pos!=0)
        {
            if (sessionManager.isNetworkAvailable()) {

                binding.progressBar.setVisibility(View.VISIBLE);

                modelList.remove(pos);

                add_to_cart_buyer(modelList.get(0).getProductDetails().getSellerId(),modelList.get(0).getProductId(), "1");

            }else {
                Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
            }

        }else
        {

            if (sessionManager.isNetworkAvailable()) {

                binding.progressBar.setVisibility(View.VISIBLE);

                add_to_cart_buyer(modelList.get(0).getProductDetails().getSellerId(),modelList.get(0).getProductId(), "1");

            }else {
                Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
            }

        }


    }
}