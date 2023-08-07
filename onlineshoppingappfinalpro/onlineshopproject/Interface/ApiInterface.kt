package com.example.onlineshopproject.Interface


import com.example.onlineshopproject.Model.DashboardModel
import com.example.onlineshopproject.Model.RegisterModel
import com.example.onlineshopproject.Model.WishlistModel
import com.example.onlineshopproject.Model.categoryModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Call

interface ApiInterface
{
    @FormUrlEncoded
    @POST("signup.php")
    fun registerdetail(
        @Field("fullname") fullname: String?,
        @Field("username") username: String?,
        @Field("email") email: String?,
        @Field("phone") phone: String?,
        @Field("password") password: String?,

        ): Call<Void>

    @FormUrlEncoded
    @POST("login.php")
    fun logindata(
        @Field("phone") phone: String?,
        @Field("pass") password: String?
    ): Call<RegisterModel>

    @GET("dashboardview.php")
    fun viewdata() : Call<List<DashboardModel>>


    @GET("women.php")
    fun womendata() : Call<List<categoryModel>>

    @GET("men.php")
    fun mendata() : Call<List<categoryModel>>

    @GET("kids.php")
    fun kiddata() : Call<List<categoryModel>>

    @GET("grocery.php")
    fun grocerydata() : Call<List<categoryModel>>

    @GET("electronic.php")
    fun electronicdata() :Call<List<categoryModel>>

    @FormUrlEncoded
    @POST("addtowishlist.php")
    fun addtowishlistdata(
        @Field("name") name : String?,
        @Field("price") price : String?,
        @Field("description") description : String?,
        @Field("image") image : String?,
    ) : Call<Void>

    @FormUrlEncoded
    @POST("addtocart.php")
    fun addtocartdata(
        @Field("name") name : String?,
        @Field("price") price : String?,
        @Field("description") description : String?,
        @Field("image") image : String?,

    ) : Call<Void>

    @FormUrlEncoded
    @POST("viewwishlist.php")
    fun viewwishlistdata() : Call<List<WishlistModel>>

    @FormUrlEncoded
    @POST("viewcart.php")
    fun viewwcartdata() : Call<List<WishlistModel>>

    @FormUrlEncoded
    @POST("wishlistdelete.php")
    fun deletewishlist(@Field("id") id : Int) : Call<Void>

    @FormUrlEncoded
    @POST("cartdelete.php")
    fun deletecart(@Field("id") id : Int) : Call<Void>




}
