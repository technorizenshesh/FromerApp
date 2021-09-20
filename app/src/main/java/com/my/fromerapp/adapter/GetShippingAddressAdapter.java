package com.my.fromerapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.my.fromerapp.R;
import com.my.fromerapp.interfacesss.DeleteAdressListener;
import com.my.fromerapp.model.GetShippingAddressData;
import com.my.fromerapp.model.GteItemProductModelData;

import java.util.ArrayList;


public class GetShippingAddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<GetShippingAddressData> modelList;
    private OnItemClickListener mItemClickListener;

    DeleteAdressListener deleteAdressListener;

    public GetShippingAddressAdapter(Context context, ArrayList<GetShippingAddressData> modelList,DeleteAdressListener deleteAdressListener) {
        this.mContext = context;
        this.modelList = modelList;
        this.deleteAdressListener = deleteAdressListener;
    }

    public void updateList(ArrayList<GetShippingAddressData> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shipping_address, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final GetShippingAddressData model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.txtAddress.setText(model.getStreetAddress1()+"\n"+model.getStreetAddress2()+"\n"+model.getCity()+","+model.getState()+","+model.getCountry());

            genericViewHolder.AddressType.setText(model.getAddressType());

            String isSelected=model.getIsSelect().toString();

            if(isSelected.equalsIgnoreCase("0"))
            {
                genericViewHolder.img_Check.setImageResource(R.drawable.circle);

            }else {

                genericViewHolder.img_Check.setImageResource(R.mipmap.check_orange);
            }

            genericViewHolder.txtDelete.setOnClickListener(v -> {

                deleteAdressListener.deleteItem(position);

            });
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

    private GetShippingAddressData getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, GetShippingAddressData model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtAddress;
        private TextView AddressType;
        private TextView txtDelete;
        private ImageView img_Check;



        public ViewHolder(final View itemView) {
            super(itemView);

         this.txtAddress=itemView.findViewById(R.id.txtAddress);
         this.AddressType=itemView.findViewById(R.id.AddressType);
         this.img_Check=itemView.findViewById(R.id.img_Check);
         this.txtDelete=itemView.findViewById(R.id.txtDelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }


}

