package com.my.fromerapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.my.fromerapp.Chat.MsgChatAct;
import com.my.fromerapp.R;
import com.my.fromerapp.model.HomeModel;
import com.my.fromerapp.model.OrderHistoryModel;
import com.my.fromerapp.model.SummeryDataModel;

import java.util.ArrayList;


public class OrderHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<OrderHistoryModel.Result> modelList;
    private OnItemClickListener mItemClickListener;
       String Type="";

    public OrderHistoryAdapter(Context context, ArrayList<OrderHistoryModel.Result> modelList,String Type) {
        this.mContext = context;
        this.modelList = modelList;
        this.Type = Type;
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

           genericViewHolder.txtSellerName.setText("Seller -: "+model.getSellerData().getName()+"");
            genericViewHolder.txtOrder_id.setText("Order id -: "+model.getOrderId());
           genericViewHolder.txtQuantity.setText(""+model.getProductCount());
           genericViewHolder.txtStatus.setText(""+model.getStatus()+"");
           genericViewHolder.txtAddress.setText(""+model.getSellerData().getFirmAddress()+"");

           if(model.getStatus().equalsIgnoreCase("Pending"))
           {
               genericViewHolder.RR_chat.setVisibility(View.GONE);

           }else
           {
               genericViewHolder.RR_chat.setVisibility(View.VISIBLE);
           }

           genericViewHolder.RR_chat.setOnClickListener(v -> {
               mContext.startActivity(new Intent(mContext, MsgChatAct.class).putExtra("SellerId",model.getSellerData().getId())
                       .putExtra("SellerName",model.getSellerData().getName())
                       .putExtra("SellerImage",model.getSellerData().getImage())
                       .putExtra("request_id",model.getId()));
           });

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

        private TextView txtSellerName;
        private TextView txtOrder_id;
        private TextView txtQuantity;
        private TextView txtStatus;
        private TextView txtAddress;
        private RelativeLayout RR_chat;


        public ViewHolder(final View itemView) {
            super(itemView);

         this.txtSellerName=itemView.findViewById(R.id.txtSellerName);
         this.txtOrder_id=itemView.findViewById(R.id.txtOrder_id);
         this.txtQuantity=itemView.findViewById(R.id.txtQuantity);
         this.txtStatus=itemView.findViewById(R.id.txtStatus);
         this.txtAddress=itemView.findViewById(R.id.txtAddress);
         this.RR_chat=itemView.findViewById(R.id.RR_chat1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }


}

