package com.my.fromerapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.my.fromerapp.R;
import com.my.fromerapp.model.HomeModel;
import com.my.fromerapp.model.ProductmySellerModelData;

import java.util.ArrayList;


public class MyOrderRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<HomeModel> modelList;
    private OnItemClickListener mItemClickListener;


    public MyOrderRecyclerViewAdapter(Context context, ArrayList<HomeModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<HomeModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_orders, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final HomeModel model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.txtName.setText(model.getName());
          //  genericViewHolder.txt_price.setText(model.getPrice()+"per KG");

        }

    }

    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private HomeModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, HomeModel model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txt_price;


        public ViewHolder(final View itemView) {
            super(itemView);

         this.txtName=itemView.findViewById(R.id.txtName);
         this.txt_price=itemView.findViewById(R.id.txt_price);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }


}

