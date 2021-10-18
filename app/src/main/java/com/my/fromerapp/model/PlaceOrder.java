package com.my.fromerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceOrder {

    @SerializedName("result")
    @Expose
    public Result result;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("status")
    @Expose
    public String status;

    public class Result {
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("order_id")
        @Expose
        public String orderId;
        @SerializedName("user_id")
        @Expose
        public String userId;
        @SerializedName("seller_id")
        @Expose
        public String sellerId;
        @SerializedName("address_id")
        @Expose
        public String addressId;
        @SerializedName("book_date")
        @Expose
        public String bookDate;
        @SerializedName("book_time")
        @Expose
        public String bookTime;
        @SerializedName("amount")
        @Expose
        public String amount;
        @SerializedName("item_id")
        @Expose
        public String itemId;
        @SerializedName("cart_id")
        @Expose
        public String cartId;
        @SerializedName("payment_type")
        @Expose
        public String paymentType;
        @SerializedName("date_time")
        @Expose
        public String dateTime;
    }
}

