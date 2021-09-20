package com.my.fromerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.my.fromerapp.act.GetCartItemsActivity;
import com.my.fromerapp.fragment.ChatFragment;
import com.my.fromerapp.fragment.HomeFragment;
import com.my.fromerapp.databinding.ActivityMainBinding;
import com.my.fromerapp.fragment.MyOrderFragment;
import com.my.fromerapp.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Fragment fragment;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.RRHome.setOnClickListener(v -> {

            binding.imgHome.setImageResource(R.mipmap.home);
            binding.imgChat.setImageResource(R.mipmap.chat);
            binding.imgProfile.setImageResource(R.mipmap.profile);
            binding.imgOrder.setImageResource(R.mipmap.my_orders);

            binding.txtHome.setTextColor(getResources().getColor(R.color.purple_200));
            binding.txtchat.setTextColor(getResources().getColor(R.color.natural_gray));
            binding.txtprofile.setTextColor(getResources().getColor(R.color.natural_gray));
            binding.txtorder.setTextColor(getResources().getColor(R.color.natural_gray));

            fragment = new HomeFragment();
            loadFragment(fragment);
        });

        binding.RRProfile.setOnClickListener(v -> {


            binding.txtHome.setTextColor(getResources().getColor(R.color.natural_gray));
            binding.txtchat.setTextColor(getResources().getColor(R.color.natural_gray));
            binding.txtprofile.setTextColor(getResources().getColor(R.color.purple_200));
            binding.txtorder.setTextColor(getResources().getColor(R.color.natural_gray));

            binding.imgHome.setImageResource(R.mipmap.home_gray);
            binding.imgChat.setImageResource(R.mipmap.chat);
            binding.imgProfile.setImageResource(R.mipmap.profile_green);
            binding.imgOrder.setImageResource(R.mipmap.my_orders);

            fragment = new ProfileFragment();
            loadFragment(fragment);

        });

        binding.RRMyOrder.setOnClickListener(v -> {

            binding.txtHome.setTextColor(getResources().getColor(R.color.natural_gray));
            binding.txtchat.setTextColor(getResources().getColor(R.color.natural_gray));
            binding.txtprofile.setTextColor(getResources().getColor(R.color.natural_gray));
            binding.txtorder.setTextColor(getResources().getColor(R.color.purple_200));


            binding.imgHome.setImageResource(R.mipmap.home_gray);
            binding.imgChat.setImageResource(R.mipmap.chat);
            binding.imgProfile.setImageResource(R.mipmap.profile);
            binding.imgOrder.setImageResource(R.mipmap.my_order_green);

            startActivity(new Intent(MainActivity.this, GetCartItemsActivity.class));

            /*fragment = new MyOrderFragment();
            loadFragment(fragment);
*/
        });

        binding.RRChat.setOnClickListener(v -> {

            binding.txtHome.setTextColor(getResources().getColor(R.color.natural_gray));
            binding.txtchat.setTextColor(getResources().getColor(R.color.purple_200));
            binding.txtprofile.setTextColor(getResources().getColor(R.color.natural_gray));
            binding.txtorder.setTextColor(getResources().getColor(R.color.natural_gray));


            binding.imgHome.setImageResource(R.mipmap.home_gray);
            binding.imgChat.setImageResource(R.mipmap.chat_green);
            binding.imgProfile.setImageResource(R.mipmap.profile);
            binding.imgOrder.setImageResource(R.mipmap.my_orders);

            fragment = new ChatFragment();
            loadFragment(fragment);
        });



        fragment = new HomeFragment();
        loadFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finishAffinity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        binding.imgHome.setImageResource(R.mipmap.home);
        binding.imgChat.setImageResource(R.mipmap.chat);
        binding.imgProfile.setImageResource(R.mipmap.profile);
        binding.imgOrder.setImageResource(R.mipmap.my_orders);

        binding.txtHome.setTextColor(getResources().getColor(R.color.purple_200));
        binding.txtchat.setTextColor(getResources().getColor(R.color.natural_gray));
        binding.txtprofile.setTextColor(getResources().getColor(R.color.natural_gray));
        binding.txtorder.setTextColor(getResources().getColor(R.color.natural_gray));

        fragment = new HomeFragment();
        loadFragment(fragment);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_homeContainer, fragment);
        transaction.addToBackStack("home");
        transaction.commit();
    }

}