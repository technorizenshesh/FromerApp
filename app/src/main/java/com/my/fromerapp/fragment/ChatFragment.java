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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.my.fromerapp.R;
import com.my.fromerapp.act.CropsActivity;
import com.my.fromerapp.act.LiveStocks;
import com.my.fromerapp.act.ProductDetails;
import com.my.fromerapp.act.SubProduct;
import com.my.fromerapp.adapter.ChatAdapter;
import com.my.fromerapp.adapter.SubProductRecyclerViewAdapter;
import com.my.fromerapp.databinding.ChatFragmentBinding;
import com.my.fromerapp.databinding.FragmentHomeBinding;
import com.my.fromerapp.model.HomeModel;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private Fragment fragment;

    ChatFragmentBinding binding;

    ChatAdapter mAdapter;
    private ArrayList<HomeModel> modelList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.chat_fragment, container, false);

        setAdapter();

        return binding.getRoot();

    }


    private void setAdapter() {

        this.modelList.add(new HomeModel("Corn"));
        this.modelList.add(new HomeModel("Tomotoes"));
        this.modelList.add(new HomeModel("Cassava"));

        mAdapter = new ChatAdapter(getActivity(),modelList);
        binding.recyclerChat.setHasFixedSize(true);
        // use a linear layout manager
        //  binding.recyclerSearch.setLayoutManager(new GridLayoutManager(this, 2));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerChat.setLayoutManager(linearLayoutManager);
        binding.recyclerChat.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new ChatAdapter.OnItemClickListener() {
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