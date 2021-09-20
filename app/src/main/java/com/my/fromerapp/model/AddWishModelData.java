package com.my.fromerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddWishModelData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("buyer_id")
    @Expose
    private String buyerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

}
