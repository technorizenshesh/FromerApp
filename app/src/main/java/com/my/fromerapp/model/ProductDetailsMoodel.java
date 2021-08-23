package com.my.fromerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetailsMoodel {

    @SerializedName("result")
    @Expose
    private ProductDetailsMoodelData result;
    @SerializedName("product")
    @Expose
    private List<ProductmySellerModelData> product = null;
    @SerializedName("review_details")
    @Expose
    private List<ReviewDetail> reviewDetails = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public ProductDetailsMoodelData getResult() {
        return result;
    }

    public void setResult(ProductDetailsMoodelData result) {
        this.result = result;
    }

    public List<ProductmySellerModelData> getProduct() {
        return product;
    }

    public void setProduct(List<ProductmySellerModelData> product) {
        this.product = product;
    }

    public List<ReviewDetail> getReviewDetails() {
        return reviewDetails;
    }

    public void setReviewDetails(List<ReviewDetail> reviewDetails) {
        this.reviewDetails = reviewDetails;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
