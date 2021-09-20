package com.my.fromerapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.my.fromerapp.R;
import com.my.fromerapp.interfacesss.CarditemListener;
import com.my.fromerapp.model.SummeryDataModel;

import java.util.ArrayList;


public class GETAllItemGetcardItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<SummeryDataModel> modelList;
    private OnItemClickListener mItemClickListener;

    public GETAllItemGetcardItemAdapter(Context context, ArrayList<SummeryDataModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<SummeryDataModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_summery, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final SummeryDataModel model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.txtName.setText(model.getProductDetails().getName());
            genericViewHolder.txt_price.setText(model.getProductDetails().getPrice()+" per KG");
            genericViewHolder.txtQuntity.setText("Quantity :"+model.getQty());

            genericViewHolder.txtDelete.setOnClickListener(v -> {

                //carditemListener.cardItem(position);

            });

            genericViewHolder.txtWishList.setOnClickListener(v -> {

               // carditemListener.WishItem(position);

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

    private SummeryDataModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, SummeryDataModel model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txt_price;
        private TextView txtDelete;
        private TextView txtQuntity;
        private TextView txtWishList;


        public ViewHolder(final View itemView) {
            super(itemView);

         this.txtName=itemView.findViewById(R.id.txtName);
         this.txt_price=itemView.findViewById(R.id.txt_price);
         this.txtDelete=itemView.findViewById(R.id.txtDelete);
         this.txtQuntity=itemView.findViewById(R.id.txtQuntity);
         this.txtWishList=itemView.findViewById(R.id.txtWishList);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }


}

