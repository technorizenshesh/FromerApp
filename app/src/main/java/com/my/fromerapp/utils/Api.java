package com.my.fromerapp.utils;

import com.my.fromerapp.model.AddToCardModel;
import com.my.fromerapp.model.AddWishModel;
import com.my.fromerapp.model.AllSumeryModel;
import com.my.fromerapp.model.ConverSationList;
import com.my.fromerapp.model.ForogtPassword;
import com.my.fromerapp.model.FrmModel;
import com.my.fromerapp.model.GetShippingAddress;
import com.my.fromerapp.model.GteItemProductModel;
import com.my.fromerapp.model.InsertChat;
import com.my.fromerapp.model.LoginModel;
import com.my.fromerapp.model.OrderHistoryModel;
import com.my.fromerapp.model.PlaceOrder;
import com.my.fromerapp.model.ProductDetailsMoodel;
import com.my.fromerapp.model.SelectedAddreessModel;
import com.my.fromerapp.model.SubCategoryModel;
import com.my.fromerapp.model.WishModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {

    String buyer_login ="buyer_login";
    String social_login ="social_login";
    String forgot_password ="forgot_password";
    String get_profile ="get_profile";
    String buyer_signup ="buyer_signup";
    String update_buyer_profile ="update_buyer_profile";
    String get_subcategory ="get_subcategory";
    String get_firm_details ="get_firm_details";
    String firm_details_byID ="firm_details_byID";
    String add_to_cart_buyer ="add_to_cart_buyer";
    String addwish ="addwish";
    String add_card_wishList ="add_card";

    String get_card_buyer ="get_card_buyer";
    String delete_card ="delete_card";
    String get_shipping_address ="get_shipping_address";
    String remove_ship_address ="remove_ship_address";
    String update_is_select ="update_is_select";
    String add_shipping_address ="add_shipping_address";
    String get_wishList ="get_wishList";
    String get_sumary ="get_sumary";
    String add_placeorder ="add_placeorder";
    String get_order_history ="get_order_history";
    String get_conversation ="get_conversation";

    //Api Call
    String insert_chat ="insert_chat";

    @FormUrlEncoded
    @POST(buyer_login)
    Call<LoginModel>login(
            @Field("email") String email,
            @Field("password") String password,
            @Field("register_id") String register_id
    );


    @FormUrlEncoded
    @POST(social_login)
    Call<LoginModel>social_login(
            @Field("social_id") String social_id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("register_id") String register_id,
            @Field("firm_name") String firm_name,
            @Field("firm_address") String firm_address,
            @Field("lat") String lat,
            @Field("lon") String lon,
            @Field("type") String type
    );

    @FormUrlEncoded
    @POST(insert_chat)
    Call<InsertChat>insert_chat(
            @Field("sender_id") String sender_id,
            @Field("receiver_id") String receiver_id,
            @Field("chat_message") String chat_message
    );

 @FormUrlEncoded
    @POST(forgot_password)
    Call<ForogtPassword>forgot_password(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST(get_profile)
    Call<LoginModel>get_profile(
            @Field("user_id") String user_id
    );

   @Multipart
    @POST(buyer_signup)
    Call<LoginModel>buyer_signup(
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("mobile") RequestBody mobile,
            @Part("password") RequestBody password,
            @Part("lat") RequestBody lat,
            @Part("lon") RequestBody lon,
            @Part("register_id") RequestBody register_id,
           @Part MultipartBody.Part part
   );

   @Multipart
    @POST(update_buyer_profile)
    Call<LoginModel>update_buyer_profile(
            @Part("user_id") RequestBody user_id,
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("mobile") RequestBody mobile,
            @Part MultipartBody.Part part
   );


    @FormUrlEncoded
    @POST(get_subcategory)
    Call<SubCategoryModel   >get_subcategory(
            @Field("cat_id") String cat_id
    );

    @FormUrlEncoded
    @POST(get_firm_details)
    Call<FrmModel>get_firm_details(
            @Field("cat_id") String cat_id,
            @Field("sub_cat_id") String sub_cat_id
    );

    @FormUrlEncoded
    @POST(firm_details_byID)
    Call<ProductDetailsMoodel>firm_details_byID(
            @Field("seller_id") String seller_id,
            @Field("buyer_id") String buyer_id
    );

    @FormUrlEncoded
    @POST(get_card_buyer)
    Call<GteItemProductModel>get_card_buyer(
            @Field("buyer_id") String buyer_id
    );

    @FormUrlEncoded
    @POST(get_sumary)
    Call<AllSumeryModel>get_sumary(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST(get_order_history)
    Call<OrderHistoryModel>get_order_history(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST(get_conversation)
    Call<ConverSationList>get_conversation(
            @Field("receiver_id") String receiver_id
    );

    @FormUrlEncoded
    @POST(get_wishList)
    Call<WishModel>get_wishList(
            @Field("buyer_id") String buyer_id
    );

    @FormUrlEncoded
    @POST(get_shipping_address)
    Call<GetShippingAddress>get_shipping_address(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST(update_is_select)
    Call<SelectedAddreessModel>update_is_select(
            @Field("user_id") String user_id,
            @Field("id") String id
    );


    @FormUrlEncoded
    @POST(add_shipping_address)
    Call<AddToCardModel>add_shipping_address(
            @Field("user_id") String user_id,
            @Field("addressType") String addressType,
            @Field("street_address1") String street_address1,
            @Field("street_address2") String street_address2,
            @Field("city") String city,
            @Field("state") String state,
            @Field("country") String country
    );

    @FormUrlEncoded
    @POST(add_to_cart_buyer)
    Call<AddToCardModel>add_to_cart_buyer(
            @Field("seller_id") String seller_id,
            @Field("buyer_id") String buyer_id,
            @Field("product_id") String product_id,
            @Field("qty") String qty
    );

    @FormUrlEncoded
    @POST(addwish)
    Call<AddWishModel>addwish(
            @Field("buyer_id") String buyer_id,
            @Field("product_id") String product_id
    );

    @FormUrlEncoded
    @POST(add_card_wishList)
    Call<AddToCardModel>add_card_wishList(
            @Field("product_id") String product_id,
            @Field("buyer_id") String buyer_id,
            @Field("seller_id") String seller_id,
            @Field("qty") String qty
    );

    @FormUrlEncoded
    @POST(delete_card)
    Call<AddToCardModel>delete_card(
            @Field("card_id") String card_id
    );

    @FormUrlEncoded
    @POST(remove_ship_address)
    Call<AddToCardModel>remove_ship_address(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST(add_placeorder)
    Call<PlaceOrder>add_placeorder(
            @Field("user_id") String user_id,
            @Field("seller_id") String seller_id,
            @Field("address_id") String address_id,
            @Field("amount") String amount,
            @Field("item_id") String item_id,
            @Field("cart_id") String cart_id,
            @Field("payment_type") String payment_type
    );

}
