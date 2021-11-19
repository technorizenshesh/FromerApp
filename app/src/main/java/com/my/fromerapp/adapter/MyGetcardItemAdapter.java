package com.my.fromerapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.my.fromerapp.Preference;
import com.my.fromerapp.R;
import com.my.fromerapp.act.GetCartItemsActivity;
import com.my.fromerapp.interfacesss.CarditemListener;
import com.my.fromerapp.model.GteItemProductModelData;

import java.util.ArrayList;


public class MyGetcardItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

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

            genericViewHolder.txtName.setText(""+model.getProductDetails().getName());
            genericViewHolder.txt_price.setText(model.getProductDetails().getPrice()+" per KG");
            genericViewHolder.txt_quantity.setText(""+model.getQty());

            genericViewHolder.txtDelete.setOnClickListener(v -> {

                carditemListener.cardItem(position);

            });

            genericViewHolder.txtWishList.setOnClickListener(v -> {

                carditemListener.WishItem(position);

            });


         genericViewHolder.RRplus.setOnClickListener(v -> {

             Preference.save(mContext,Preference.KEY_seller_id,model.getSellerId());

                String quntity =genericViewHolder.txt_quantity.getText().toString();
                int i= Integer.parseInt(quntity);
                i++;
                carditemListener.addItem(position,i);
                genericViewHolder.txt_quantity.setText(i + "");

            });

            genericViewHolder.RR_mnus1.setOnClickListener(v -> {

                Preference.save(mContext,Preference.KEY_seller_id,model.getSellerId());

                String quntity =genericViewHolder.txt_quantity.getText().toString();
                int i= Integer.parseInt(quntity);
                if (i >1) {
                    i--;
                    carditemListener.addItem(position,i);
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
        private TextView txtWishList;
        private TextView txt_quantity;
        private RelativeLayout RRplus;
        private RelativeLayout RR_mnus1;


        public ViewHolder(final View itemView) {
            super(itemView);

         this.txtName=itemView.findViewById(R.id.txtName);
         this.txt_price=itemView.findViewById(R.id.txt_price);
         this.txtDelete=itemView.findViewById(R.id.txtDelete);
         this.txtQuntity=itemView.findViewById(R.id.txtQuntity);
         this.txtWishList=itemView.findViewById(R.id.txtWishList);
         this.RRplus=itemView.findViewById(R.id.RRplus);
         this.RR_mnus1=itemView.findViewById(R.id.RR_mnus1);
         this.txt_quantity=itemView.findViewById(R.id.txt_quantity);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));

                }
            });
        }
    }


}

