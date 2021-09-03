package com.my.fromerapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.my.fromerapp.R;
import com.my.fromerapp.interfacesss.CarditemListener;
import com.my.fromerapp.model.GteItemProductModelData;

import java.util.ArrayList;


public class MyGetcardItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<GteItemProductModelData> modelList;
    private OnItemClickListener mItemClickListener;

    CarditemListener carditemListener;


    public MyGetcardItemAdapter(Context context, ArrayList<GteItemProductModelData> modelList, CarditemListener carditemListener) {
        this.mContext = context;
        this.modelList = modelList;
        this.carditemListener = carditemListener;
    }

    public void updateList(ArrayList<GteItemProductModelData> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_card_get_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final GteItemProductModelData model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.txtName.setText(model.getProductDetails().getName());
            genericViewHolder.txt_price.setText(model.getProductDetails().getPrice()+" per KG");
            genericViewHolder.txtQuntity.setText("Quantity :"+model.getQty());

            genericViewHolder.txtDelete.setOnClickListener(v -> {

                carditemListener.cardItem(position);

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

    private GteItemProductModelData getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, GteItemProductModelData model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txt_price;
        private TextView txtDelete;
        private TextView txtQuntity;


        public ViewHolder(final View itemView) {
            super(itemView);

         this.txtName=itemView.findViewById(R.id.txtName);
         this.txt_price=itemView.findViewById(R.id.txt_price);
         this.txtDelete=itemView.findViewById(R.id.txtDelete);
         this.txtQuntity=itemView.findViewById(R.id.txtQuntity);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }


}

