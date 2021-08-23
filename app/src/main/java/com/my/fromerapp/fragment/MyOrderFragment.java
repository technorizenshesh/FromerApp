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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.my.fromerapp.R;
import com.my.fromerapp.act.CropsActivity;
import com.my.fromerapp.act.EditProfile;
import com.my.fromerapp.act.SubProduct;
import com.my.fromerapp.adapter.HomeSaloonRecyclerViewAdapter;
import com.my.fromerapp.adapter.MyOrderRecyclerViewAdapter;
import com.my.fromerapp.databinding.FragmentMyOrderBinding;
import com.my.fromerapp.databinding.FragmentProfileBinding;
import com.my.fromerapp.model.HomeModel;

import java.util.ArrayList;


public class MyOrderFragment extends Fragment {

    private Fragment fragment;

    FragmentMyOrderBinding binding;
    MyOrderRecyclerViewAdapter mAdapter;
    private ArrayList<HomeModel> modelList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_order, container, false);

        setAdapter();

        binding.txtConfirm.setOnClickListener(v -> {

            fragment = new CardFragment();
            loadFragment(fragment);
        });

        return binding.getRoot();
    }

    private void setAdapter() {

        this.modelList.add(new HomeModel("Corn"));
        this.modelList.add(new HomeModel("Tomotoes"));
        this.modelList.add(new HomeModel("Cassava"));

        mAdapter = new MyOrderRecyclerViewAdapter(getActivity(),modelList);
        binding.recycler.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
         binding.recycler.setLayoutManager(linearLayoutManager);
        binding.recycler.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new MyOrderRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, HomeModel model) {

            }
        });
    }


    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_homeContainer, fragment);
        transaction.addToBackStack("home");
        transaction.commit();
    }


}