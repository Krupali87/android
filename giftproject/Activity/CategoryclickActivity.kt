package com.example.giftshopproject.Activity


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.giftshopproject.Adapter.categoryAdapter
import com.example.giftshopproject.Interface.Apiinterface
import com.example.giftshopproject.Model.WishlistModel
import com.example.giftshopproject.R

import com.example.giftshopproject.client.ApiClient

import com.example.giftshopproject.databinding.ActivityCategoryclickBinding

import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class CategoryclickActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryclickBinding
    lateinit var apiinterface: Apiinterface
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCategoryclickBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        apiinterface = ApiClient.getapiclient()!!.create(Apiinterface::class.java)
        sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)
        var mobile=sharedPreferences.getString("mobile", "")
        val i = intent
        val giftImage = i.getStringExtra("image")
        val giftName = i.getStringExtra("name")
        val giftPrice = i.getStringExtra("price")
        val giftDesc = i.getStringExtra("description")

        Picasso.get().load(giftImage).into(binding.photoview)
        binding.giftName.text = giftName
        binding.giftPrice.text = giftPrice
        binding.giftDesc.text = giftDesc


        binding.wishListButton.setOnClickListener {

           val call = apiinterface.addtowishlistdata(giftName, giftPrice, giftDesc, giftImage,mobile)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(applicationContext, "Added to wishlist", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }

        binding.cartButton.setOnClickListener {
            val call = apiinterface.addtocartdata(giftName,giftPrice,giftDesc,giftImage,mobile)
            call.enqueue(object : retrofit2.Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>)
                {
                    Toast.makeText(applicationContext, "Add toCart", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable)
                {
                    Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.dashboardmenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {

            R.id.wish->
            {
                    startActivity(Intent(applicationContext,WishlistActivity::class.java))
            }
            R.id.cart->
            {
                startActivity(Intent(applicationContext,CartActivity::class.java))
            }
            R.id.i1->
            {
                sharedPreferences.edit().clear().commit()
                finish()
                startActivity(Intent(applicationContext,loginActivity::class.java))
            }

        }
        return super.onOptionsItemSelected(item)
    }
}