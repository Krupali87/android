package com.example.onlineshopproject.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.onlineshopproject.Interface.ApiInterface
import com.example.onlineshopproject.R
import retrofit2.Call
import com.example.onlineshopproject.client.ApiClient
import com.example.onlineshopproject.databinding.ActivityCategoryItemClickBinding

import com.squareup.picasso.Picasso
import retrofit2.Callback
import retrofit2.Response

class CategoryItemClickActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryItemClickBinding
    lateinit var apiinterface: ApiInterface
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryItemClickBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar!!.title = "Show Product"

        apiinterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)
        sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)

        val i = intent
        val Image = i.getStringExtra("image")
        val Name = i.getStringExtra("name")
        val Price = i.getStringExtra("price")
        val Desc = i.getStringExtra("description")

        Picasso.get().load(Image).into(binding.photoview)
        binding.name.text = Name
        binding.price.text = Price
        binding.desc.text = Desc

        binding.wishListButton.setOnClickListener {

            val call = apiinterface.addtowishlistdata(Name, Price, Desc, Image)
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

            val call = apiinterface.addtocartdata(Name, Price, Desc, Image)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(applicationContext, "Added to cart", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
                }

            })
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.option,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.wish ->
            {
                startActivity(Intent(applicationContext,WishlistActivity::class.java))
            }
            R.id.cart ->
            {
                startActivity(Intent(applicationContext,CartActivity::class.java))
            }
            R.id.i1 ->

            {
                sharedPreferences.edit().clear().commit()
                finish()
                startActivity(Intent(applicationContext,LoginActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}