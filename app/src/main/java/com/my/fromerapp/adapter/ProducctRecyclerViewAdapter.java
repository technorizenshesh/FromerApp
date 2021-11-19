package com.my.fromerapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.my.fromerapp.R;
import com.my.fromerapp.interfacesss.additemListener;
import com.my.fromerapp.interfacesss.additemNewListener;
import com.my.fromerapp.model.ProductmySellerModelData;

import java.util.ArrayList;


public class ProducctRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<ProductmySellerModelData> modelList;
    private OnItemClickListener mItemClickListener;
    additemListener additemListener;

    public ProducctRecyclerViewAdapter(Context context, ArrayList<ProductmySellerModelData> modelList, additemListener additemListener) {
        this.mContext = context;
        this.modelList = modelList;
        this.additemListener = additemListener;
    }

    public void updateList(ArrayList<ProductmySellerModelData> modelList) {
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
            final ProductmySellerModelData model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;


            String product_status = model.getProductStatus().toString();
           // i= Integer.parseInt(model.getQuantity().toString());
            if (product_status.equalsIgnoreCase("true")) {
                genericViewHolder.ll_additem.setVisibility(View.VISIBLE);
                genericViewHolder.txtAdd.setVisibility(View.GONE);

            } else {
                genericViewHolder.ll_additem.setVisibility(View.GONE);
                genericViewHolder.txtAdd.setVisibility(View.VISIBLE);

            }

            genericViewHolder.txtName.setText(model.getName());
            genericViewHolder.txt_quantity.setText(model.getQuantity());
            genericViewHolder.txt_price.setText(model.getPrice() + "per KG");

            genericViewHolder.txtAdd.setOnClickListener(v -> {

                additemListener.addItem(position);

            });

            Glide.with(mContext).load(model.getImage()).placeholder(R.mipmap.img_home_one).into(genericViewHolder.img1);

            genericViewHolder.RRplus.setOnClickListener(v -> {

                String quntity =genericViewHolder.txt_quantity.getText().toString();
                int i= Integer.parseInt(quntity);
                i++;
                additemListener.addItem(position,i);
                genericViewHolder.txt_quantity.setText(i + "");

            });

            genericViewHolder.RR_mnus1.setOnClickListener(v -> {
                String quntity =genericViewHolder.txt_quantity.getText().toString();
                int i= Integer.parseInt(quntity);
                if (i >1) {
                    i--;
                    additemListener.addItem(position,i);
                    genericViewHolder.txt_quantity.setText(i + "");
                }
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

    private ProductmySellerModelData getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, ProductmySellerModelData model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txt_quantity;
        private TextView txt_price;
        private TextView txtAdd;
        private LinearLayout ll_additem;
        private ImageView img1;

        private RelativeLayout RR_mnus1;
        private RelativeLayout RRplus;


        public ViewHolder(final View itemView) {
            super(itemView);

            this.txtName = itemView.findViewById(R.id.txtName);
            this.txt_price = itemView.findViewById(R.id.txt_price);
            this.img1 = itemView.findViewById(R.id.img1);
            this.txtAdd = itemView.findViewById(R.id.txtAdd);
            this.ll_additem = itemView.findViewById(R.id.ll_additem);
            this.txt_quantity = itemView.findViewById(R.id.txt_quantity);
            this.RR_mnus1 = itemView.findViewById(R.id.RR_mnus1);
            this.RRplus = itemView.findViewById(R.id.RRplus);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));

                }
            });
        }
    }


}

