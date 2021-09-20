package com.my.fromerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SummeryDataModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("seller_id")
    @Expose
    private String sellerId;
    @SerializedName("buyer_id")
    @Expose
    private String buyerId;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("product_details")
    @Expose
    private ProductDetailsModel productDetails;
    @SerializedName("seller_details")
    @Expose
    private SellerDetailsModel sellerDetails;
    @SerializedName("image")
    @Expose
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public ProductDetailsModel getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetailsModel productDetails) {
        this.productDetails = productDetails;
    }

    public SellerDetailsModel getSellerDetails() {
        return sellerDetails;
    }

    public void setSellerDetails(SellerDetailsModel sellerDetails) {
        this.sellerDetails = sellerDetails;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
