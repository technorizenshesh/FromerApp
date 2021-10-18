package com.my.fromerapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.my.fromerapp.R;
import com.my.fromerapp.model.HomeModel;
import com.my.fromerapp.model.OrderHistoryModel;
import com.my.fromerapp.model.SummeryDataModel;

import java.util.ArrayList;


public class OrderHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<OrderHistoryModel.Result> modelList;
    private OnItemClickListener mItemClickListener;


    public OrderHistoryAdapter(Context context, ArrayList<OrderHistoryModel.Result> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<OrderHistoryModel.Result> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order_history, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final OrderHistoryModel.Result model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

           genericViewHolder.txtOrder_id.setText("Order id -: "+model.orderId+"");
           genericViewHolder.txtAmt.setText("Total Amount -: "+model.amount+"");

        }

    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private OrderHistoryModel.Result getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, OrderHistoryModel.Result model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtOrder_id;
        private TextView txtAmt;


        public ViewHolder(final View itemView) {
            super(itemView);

         this.txtOrder_id=itemView.findViewById(R.id.txtOrder_id);
         this.txtAmt=itemView.findViewById(R.id.txtAmt);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }


}

