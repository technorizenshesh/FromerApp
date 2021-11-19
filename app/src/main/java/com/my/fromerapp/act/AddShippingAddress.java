package com.my.fromerapp.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.my.fromerapp.MainActivity;
import com.my.fromerapp.Preference;
import com.my.fromerapp.R;
import com.my.fromerapp.databinding.ActivityAddShippingAddressBinding;
import com.my.fromerapp.model.AddToCardModel;
import com.my.fromerapp.model.GetShippingAddress;
import com.my.fromerapp.model.LoginModel;
import com.my.fromerapp.utils.RetrofitClients;
import com.my.fromerapp.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class AddShippingAddress extends AppCompatActivity {

    ActivityAddShippingAddressBinding binding;
    String AdressType = "";
    String Street1 = "";
    String Street2 = "";
    String city = "";
    String State = "";
    String Country = "";
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_shipping_address);

      binding.RRBack.setOnClickListener(v -> {

            onBackPressed();
        });

        binding.txtAddAddress.setOnClickListener(v -> {

            Validation();

        });


    }

    private void Validation() {
        AdressType = binding.etAdressType.getText().toString();
        Street1 = binding.streetAddress1.getText().toString();
        Street2 = binding.streetAddress2.getText().toString();
        city = binding.edtCity.getText().toString();
        State = binding.etState.getText().toString();
        Country = binding.etCountry.getText().toString();

        if (Street1.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please Enter Street 1", Toast.LENGTH_SHORT).show();
        } else if (Street2.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please Enter Street 2", Toast.LENGTH_SHORT).show();

        } else if (city.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please Enter City", Toast.LENGTH_SHORT).show();

        } else if (State.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please Enter State", Toast.LENGTH_SHORT).show();

        } else if (Country.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please Enter Country", Toast.LENGTH_SHORT).show();

        } else {
            sessionManager = new SessionManager(AddShippingAddress.this);

            if (sessionManager.isNetworkAvailable()) {
                binding.progressBar.setVisibility(VISIBLE);
                add_shipping();

            } else {
                Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void add_shipping(){

        String  User_id=Preference.get(AddShippingAddress.this,Preference.KEY_user_id);

        Call<AddToCardModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .add_shipping_address(User_id,AdressType,Street1,Street2,city,State,Country);

        call.enqueue(new Callback<AddToCardModel>() {
            @Override
            public void onResponse(Call<AddToCardModel> call, Response<AddToCardModel> response) {

                binding.progressBar.setVisibility(View.GONE);

                AddToCardModel finallyPr = response.body();

                String status = finallyPr.getStatus();

                if (status.equalsIgnoreCase("1")) {

                    startActivity(new Intent(AddShippingAddress.this, ShipppingAddress.class).putExtra("Type","other"));

                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(AddShippingAddress.this, finallyPr.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<AddToCardModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                startActivity(new Intent(AddShippingAddress.this, ShipppingAddress.class).putExtra("Type","other"));
            }
        });
    }

}