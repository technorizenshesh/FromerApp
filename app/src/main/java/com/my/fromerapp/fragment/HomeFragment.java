package com.my.fromerapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.my.fromerapp.MainActivity;
import com.my.fromerapp.Preference;
import com.my.fromerapp.R;
import com.my.fromerapp.act.CropsActivity;
import com.my.fromerapp.act.LiveStocks;
import com.my.fromerapp.act.Login;
import com.my.fromerapp.act.Notification;
import com.my.fromerapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private Fragment fragment;

    FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        binding.imgCrops.setOnClickListener(v -> {

            Preference.save(getActivity(),Preference.KEYcategory_id,"1");
            Preference.save(getActivity(),Preference.KEYLivestock,"Crops");

            startActivity(new Intent(getActivity(), CropsActivity.class));

        });

        binding.RRnotification.setOnClickListener(v -> {

            startActivity(new Intent(getActivity(), Notification.class));

        });

        binding.imgLive.setOnClickListener(v -> {

            Preference.save(getActivity(),Preference.KEYcategory_id,"2");
            Preference.save(getActivity(),Preference.KEYLivestock,"Livestock");
            startActivity(new Intent(getActivity(), CropsActivity.class));

        });

        return binding.getRoot();

    }


    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_homeContainer, fragment);
        transaction.addToBackStack("home");
        transaction.commit();
    }


}