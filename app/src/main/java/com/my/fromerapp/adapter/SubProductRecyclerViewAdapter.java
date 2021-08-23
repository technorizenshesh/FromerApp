package com.my.fromerapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.my.fromerapp.R;
import com.my.fromerapp.model.FrmModelData;
import com.my.fromerapp.model.HomeModel;

import java.util.ArrayList;


public class SubProductRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<FrmModelData> modelList;
    private OnItemClickListener mItemClickListener;


    public SubProductRecyclerViewAdapter(Context context, ArrayList<FrmModelData> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<FrmModelData> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sub_product, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final FrmModelData model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.txt_frmName.setText(model.getFirmName());
            genericViewHolder.txt_address.setText(model.getFirmAddress());

            String Url= model.getFirmImage().toString();

            Glide.with(mContext).load(Url).placeholder(R.mipmap.img_home_one).into(genericViewHolder.img1);

        }
    }

    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private FrmModelData getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, FrmModelData model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_frmName;
        private TextView txt_address;
        private ImageView img1;


        public ViewHolder(final View itemView) {
            super(itemView);

            this.txt_frmName=itemView.findViewById(R.id.txt_frmName);
            this.txt_address=itemView.findViewById(R.id.txt_address);
            this.img1=itemView.findViewById(R.id.img1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }


}

