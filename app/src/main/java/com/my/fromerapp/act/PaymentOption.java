package com.my.fromerapp.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.my.fromerapp.MainActivity;
import com.my.fromerapp.Preference;
import com.my.fromerapp.R;
import com.my.fromerapp.databinding.ActivityPaymentOptionBinding;
import com.my.fromerapp.model.AllSumeryModel;
import com.my.fromerapp.model.PlaceOrder;
import com.my.fromerapp.model.SummeryDataModel;
import com.my.fromerapp.utils.RetrofitClients;
import com.my.fromerapp.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class PaymentOption extends AppCompatActivity {

    ActivityPaymentOptionBinding binding;
    private SessionManager sessionManager;

    String Item_id ="";
    String card_id ="";
    String Address_id ="";
    String Seller_id ="";
    String Amount ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_payment_option);

        sessionManager = new SessionManager(PaymentOption.this);

        Intent intent=getIntent();

        if(intent!=null)
        {
            Item_id = intent.getStringExtra("item_id").toString();
            card_id = intent.getStringExtra("card_id").toString();
            Address_id = intent.getStringExtra("Address_id").toString();
            Seller_id = intent.getStringExtra("Seller_id").toString();
            Amount = intent.getStringExtra("Amount").toString();
        }


        binding.txtProcced.setOnClickListener(v -> {


            if (sessionManager.isNetworkAvailable()) {

                binding.progressBar.setVisibility(VISIBLE);

                getPlaceOrderItem();

            }else {
                Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void getPlaceOrderItem() {

        String buyer_id = Preference.get(PaymentOption.this,Preference.KEY_user_id);

        Call<PlaceOrder> call = RetrofitClients
                .getInstance()
                .getApi()
                .add_placeorder(buyer_id,Seller_id,Address_id,Amount,Item_id,card_id,"cash");
        call.enqueue(new Callback<PlaceOrder>() {
            @Override
            public void onResponse(Call<PlaceOrder> call, Response<PlaceOrder> response) {
                try {

                    binding.progressBar.setVisibility(GONE);

                    PlaceOrder myclass = response.body();

                    String status = myclass.status;
                    String message = myclass.message;

                    if (status.equalsIgnoreCase("1")) {


                        Toast.makeText(PaymentOption.this, message+"", Toast.LENGTH_SHORT).show();

                       startActivity(new Intent(PaymentOption.this, MainActivity.class));

                    } else {

                        Toast.makeText(PaymentOption.this, message+"", Toast.LENGTH_SHORT).show();
                    }

                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<PlaceOrder> call, Throwable t) {
                binding.progressBar.setVisibility(GONE);
                Toast.makeText(PaymentOption.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}