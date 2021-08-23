package com.my.fromerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewDetail {

    @SerializedName("buyer_name")
    @Expose
    private String buyerName;
    @SerializedName("buyer_image")
    @Expose
    private String buyerImage;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("buyer_review")
    @Expose
    private String buyerReview;

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerImage() {
        return buyerImage;
    }

    public void setBuyerImage(String buyerImage) {
        this.buyerImage = buyerImage;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getBuyerReview() {
        return buyerReview;
    }

    public void setBuyerReview(String buyerReview) {
        this.buyerReview = buyerReview;
    }

}
