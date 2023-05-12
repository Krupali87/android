package com.example.giftshopproject.Interface

import com.example.giftshopproject.Model.DashboardModel
import com.example.giftshopproject.Model.RegisterModel
import com.example.giftshopproject.Model.WishlistModel
import com.example.giftshopproject.Model.categoryModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface Apiinterface
{
    @FormUrlEncoded
    @POST("signup.php")
    fun registerdetail(
        @Field("firstname") firstname: String?,
        @Field("lastname") lastname: String?,
        @Field("gender") gender:String?,
        @Field("email") email: String?,
        @Field("phone") mobile: String?,
        @Field("password") password: String?,

        ): Call<Void>

    @FormUrlEncoded
    @POST("login.php")
    fun logindata(
        @Field("phone") mobile: String?,
        @Field("pass") password: String?
    ): Call<RegisterModel>

    @GET("giftview.php")
    fun viewdata() : Call<List<DashboardModel>>

    @GET("souvenirs.php")
    fun souvenirsdata() : Call<List<categoryModel>>

    @GET("clothingandaccessories.php")
    fun clothandaccesssories() : Call<List<categoryModel>>

    @GET("toysandgames.php")
    fun toysandgames() : Call<List<categoryModel>>

    @GET("foodanddrink.php")
    fun foodanddrink() : Call<List<categoryModel>>

    @GET("homedecorandfurnish.php")
    fun homeanddecorefurnish() : Call<List<categoryModel>>

    @GET("BooksandStationery.php")
    fun bookandstationery() : Call<List<categoryModel>>

    @GET("personalcareproduct.php")
    fun personalcareproduct() : Call<List<categoryModel>>

    @GET("specialoccasiongift.php")
    fun specialoccessories() : Call<List<categoryModel>>

    @GET("flowerandplant.php")
    fun flowersandplant() : Call<List<categoryModel>>

    @FormUrlEncoded
    @POST("addtowishlist.php")
    fun addtowishlistdata(
        @Field("gift_name") gift_name : String?,
        @Field("gift_price") gift_price : String?,
        @Field("gift_description") gift_description : String?,
        @Field("gift_image") gift_image : String?,
        @Field("mobile") mobile : String?,
    ) : Call<Void>

    @FormUrlEncoded
    @POST("addtocart.php")
    fun addtocartdata(
        @Field("gift_name") gift_name : String?,
        @Field("gift_price") gift_price : String?,
        @Field("gift_description") gift_description : String?,
        @Field("gift_image") gift_image : String?,
        @Field("mobile") mobile : String?,
    ) : Call<Void>

    @FormUrlEncoded
    @POST("viewwishlist.php")
    fun viewwishlistdata(
        @Field("mobile") mobile: String?,
    ) : Call<List<WishlistModel>>


    @FormUrlEncoded
    @POST("viewcart.php")
    fun viewwcartdata(
        @Field("mobile") mobile: String?,
    ) : Call<List<WishlistModel>>


    @FormUrlEncoded
    @POST("wishlistdelete.php")
    fun deletewishlist(@Field("id") id : Int) : Call<Void>

    @FormUrlEncoded
    @POST("cartdelete.php")
    fun deletecart(@Field("id") id : Int) : Call<Void>














}

