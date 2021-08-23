package com.my.fromerapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.my.fromerapp.R;
import com.my.fromerapp.model.HomeModel;
import com.my.fromerapp.model.SubCategoryModelData;

import java.util.ArrayList;


public class HomeSaloonRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<SubCategoryModelData> modelList;
    private OnItemClickListener mItemClickListener;


    public HomeSaloonRecyclerViewAdapter(Context context, ArrayList<SubCategoryModelData> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<SubCategoryModelData> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_crops, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final SubCategoryModelData model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;
            genericViewHolder.txtName.setText(model.getName());
        }

    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private SubCategoryModelData getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, SubCategoryModelData model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;


        public ViewHolder(final View itemView) {
            super(itemView);

         this.txtName=itemView.findViewById(R.id.txtName);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }


}

