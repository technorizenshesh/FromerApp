package com.my.fromerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllSumeryModel {


    @SerializedName("result")
    @Expose
    private List<SummeryDataModel> result = null;
    @SerializedName("total_amount")
    @Expose
    private Double totalAmount;
    @SerializedName("selected_address")
    @Expose
    private SelectedAddressModel selectedAddress;
    @SerializedName("selected_card")
    @Expose
    private SelectedCardModel selectedCard;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public List<SummeryDataModel> getResult() {
        return result;
    }

    public void setResult(List<SummeryDataModel> result) {
        this.result = result;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public SelectedAddressModel getSelectedAddress() {
        return selectedAddress;
    }

    public void setSelectedAddress(SelectedAddressModel selectedAddress) {
        this.selectedAddress = selectedAddress;
    }

    public SelectedCardModel getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(SelectedCardModel selectedCard) {
        this.selectedCard = selectedCard;
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
