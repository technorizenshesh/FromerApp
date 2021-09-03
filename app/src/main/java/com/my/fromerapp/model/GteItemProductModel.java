package com.my.fromerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GteItemProductModel {

    @SerializedName("result")
    @Expose
    private List<GteItemProductModelData> result = null;
    @SerializedName("total_amount")
    @Expose
    private Integer totalAmount;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public List<GteItemProductModelData> getResult() {
        return result;
    }

    public void setResult(List<GteItemProductModelData> result) {
        this.result = result;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
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
