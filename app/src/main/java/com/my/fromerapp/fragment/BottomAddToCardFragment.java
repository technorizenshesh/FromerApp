package com.my.fromerapp.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.my.fromerapp.Preference;
import com.my.fromerapp.R;
import com.my.fromerapp.act.ProductDetails;
import com.my.fromerapp.model.AddToCardModel;
import com.my.fromerapp.utils.RetrofitClients;
import com.my.fromerapp.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BottomAddToCardFragment extends BottomSheetDialogFragment {

    RelativeLayout RRAdd;
    RelativeLayout RR_mnus;
    RelativeLayout RRplus;
    TextView txt_quatity;

    int i=1;
    String Product_id="";
    String ProductName="";
    String ProductPrice="";
    String ProductImage="";

    TextView txtName;
    TextView txt_price;
    ImageView img1;
    Context context;
    private SessionManager sessionManager;

    public BottomAddToCardFragment(String Product_id, String ProductName, String ProductPrice, String ProductImage, Context context) {
    this.Product_id=Product_id;
    this.ProductName=ProductName;
    this.ProductPrice=ProductPrice;
    this.ProductImage=ProductImage;
    this.context=context;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.fragment_bottom_addtocard, (ViewGroup) null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));

        RRAdd=contentView.findViewById(R.id.RRAdd);
        RR_mnus=contentView.findViewById(R.id.RR_mnus);
        RRplus=contentView.findViewById(R.id.RRplus);
        txt_quatity=contentView.findViewById(R.id.txt_quatity);
        txtName=contentView.findViewById(R.id.txtName);
        txt_price=contentView.findViewById(R.id.txt_price);
        img1=contentView.findViewById(R.id.img1);

        txtName.setText(ProductName);
        txt_price.setText("Rs."+ProductPrice+"per KG");

        sessionManager = new SessionManager(getActivity());

        Glide.with(context).load(ProductImage).placeholder(R.mipmap.img_home_one).into(img1);


        RRAdd.setOnClickListener(v -> {

            dialog.dismiss();

            if (sessionManager.isNetworkAvailable()) {
               
                add_to_cart_buyer(Product_id, String.valueOf(i));

            }else {
                Toast.makeText(context, R.string.checkInternet, Toast.LENGTH_SHORT).show();
            }

        });

        RRplus.setOnClickListener(v -> {

           i++;
            txt_quatity.setText(i+"");
        });

        RR_mnus.setOnClickListener(v -> {
            if (i>1)
            {
                i--;
                txt_quatity.setText(i+"");
            }
        });

    }


    private void add_to_cart_buyer(String ProductId,String quntity){

        String Seller_id = Preference.get(context,Preference.KEY_seller_id);
        String buyer_id = Preference.get(context,Preference.KEY_user_id);


        Call<AddToCardModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .add_to_cart_buyer(Seller_id,buyer_id,ProductId,quntity);

        call.enqueue(new Callback<AddToCardModel>() {
            @Override
            public void onResponse(Call<AddToCardModel> call, Response<AddToCardModel> response) {

                AddToCardModel finallyPr = response.body();

                String status = finallyPr.getStatus();

                if (status.equalsIgnoreCase("1")) {

                    ((ProductDetails)context).getFrmdetailsProductseller();

                    Toast.makeText(context, finallyPr.getResult()+"", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, finallyPr.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<AddToCardModel> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }
}
