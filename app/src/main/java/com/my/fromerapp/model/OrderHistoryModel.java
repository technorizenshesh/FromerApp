package com.my.fromerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderHistoryModel {

    @SerializedName("result")
    @Expose
    public List<Result> result = null;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("status")
    @Expose
    public String status;


    public class ProductList {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("seller_id")
        @Expose
        public String sellerId;
        @SerializedName("category_id")
        @Expose
        public String categoryId;
        @SerializedName("sub_cat_id")
        @Expose
        public String subCatId;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("lat")
        @Expose
        public String lat;
        @SerializedName("lon")
        @Expose
        public String lon;
        @SerializedName("price")
        @Expose
        public String price;
        @SerializedName("description")
        @Expose
        public String description;
        @SerializedName("stock_status")
        @Expose
        public String stockStatus;
        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("date_time")
        @Expose
        public String dateTime;
    }

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
        @SerializedName("product_list")
        @Expose
        public ProductList productList;

    }
}
