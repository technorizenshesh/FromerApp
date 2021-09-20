package com.my.fromerapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.my.fromerapp.R;
import com.my.fromerapp.interfacesss.CarditemListener;
import com.my.fromerapp.interfacesss.WishlistListener;
import com.my.fromerapp.model.GteItemProductModelData;
import com.my.fromerapp.model.WishModel;
import com.my.fromerapp.model.WishModelData;

import java.util.ArrayList;


public class MyWishlistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<WishModelData> modelList;
    private OnItemClickListener mItemClickListener;

    WishlistListener carditemListener;

    public MyWishlistAdapter(Context context, ArrayList<WishModelData> modelList,WishlistListener carditemListener) {
        this.mContext = context;
        this.modelList = modelList;
        this.carditemListener = carditemListener;
    }

    public void updateList(ArrayList<WishModelData> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_wish_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final WishModelData model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.txtName.setText(model.getProductDetails().getName());
            genericViewHolder.txt_price.setText("Rs."+model.getProductDetails().getPrice()+" per KG");


            genericViewHolder.txt_add.setOnClickListener(v -> {

                carditemListener.Wishlist(position);
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

    private WishModelData getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, WishModelData model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txt_price;
        TextView txt_add;

        public ViewHolder(final View itemView) {
            super(itemView);

        this.txtName=itemView.findViewById(R.id.txtName);
        this.txt_price=itemView.findViewById(R.id.txt_price);
        this.txt_add=itemView.findViewById(R.id.txt);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }


}

