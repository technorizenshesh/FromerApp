package com.my.fromerapp.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.my.fromerapp.R;
import com.my.fromerapp.adapter.MyOrderRecyclerViewAdapter;
import com.my.fromerapp.databinding.FragmeentSummeryBinding;
import com.my.fromerapp.databinding.FragmentCardBinding;
import com.my.fromerapp.model.HomeModel;

import java.util.ArrayList;


public class SummeryFragment extends Fragment {

    private Fragment fragment;

    FragmeentSummeryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       binding = DataBindingUtil.inflate(inflater, R.layout.fragmeent_summery, container, false);

       binding.RRBack.setOnClickListener(v -> {
           getActivity().onBackPressed();
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