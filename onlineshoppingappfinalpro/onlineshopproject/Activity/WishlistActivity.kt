package com.example.onlineshopproject.Activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshopproject.Adapter.WishlistAdapter
import com.example.onlineshopproject.Interface.ApiInterface
import com.example.onlineshopproject.Model.WishlistModel
import com.example.onlineshopproject.R
import com.example.onlineshopproject.client.ApiClient
import com.example.onlineshopproject.databinding.ActivityWishlistBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WishlistActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityWishlistBinding
    lateinit var apiinterface: ApiInterface
    lateinit var list: MutableList<WishlistModel>
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityWishlistBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar!!.title = "Wishlist"

        list = ArrayList()
        apiinterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)

        binding.recycler.layoutManager = LinearLayoutManager(this)

        sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)

        apiinterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)

        val call : Call<List<WishlistModel>> = apiinterface.viewwishlistdata()
        call.enqueue(object : Callback<List<WishlistModel>>{
            override fun onResponse(
                call: Call<List<WishlistModel>>,
                response: Response<List<WishlistModel>>
            ) {
                list = response.body() as MutableList<WishlistModel>

                val myAdapter = WishlistAdapter(applicationContext,list)
                binding.recycler.adapter = myAdapter
            }

            override fun onFailure(call: Call<List<WishlistModel>>, t: Throwable) {
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
            }


        })
    }
}