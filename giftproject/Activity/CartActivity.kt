package com.example.giftshopproject.Activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.giftshopproject.Adapter.CartAdapter
import com.example.giftshopproject.Interface.Apiinterface
import com.example.giftshopproject.Model.WishlistModel
import com.example.giftshopproject.client.ApiClient
import com.example.giftshopproject.databinding.ActivityCartBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityCartBinding
    lateinit var list: MutableList<WishlistModel>
    private lateinit var apiinterface: Apiinterface
   private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        list = ArrayList()
        apiinterface = ApiClient.getapiclient()!!.create(Apiinterface::class.java)

        binding.recycler.layoutManager = LinearLayoutManager(this)

        sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)
        var mobile =sharedPreferences.getString("mobile","")

        apiinterface = ApiClient.getapiclient()!!.create(Apiinterface::class.java)
        val call: Call<List<WishlistModel>> = apiinterface.viewwcartdata(mobile)
        call.enqueue(object : Callback<List<WishlistModel>> {

            override fun onResponse(
                call: Call<List<WishlistModel>>,
                response: Response<List<WishlistModel>>
            )
            {
                list = response.body() as MutableList<WishlistModel>

                val myAdapter = CartAdapter(applicationContext, list)
                binding.recycler.adapter = myAdapter

            }

            override fun onFailure(call: Call<List<WishlistModel>>, t: Throwable) {
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
            }
        })






    }
}