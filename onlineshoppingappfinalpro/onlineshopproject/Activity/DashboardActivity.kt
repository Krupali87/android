package com.example.onlineshopproject.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopproject.Adapter.DashboardAdapter
import com.example.onlineshopproject.Interface.ApiInterface
import com.example.onlineshopproject.Model.DashboardModel
import com.example.onlineshopproject.R
import com.example.onlineshopproject.client.ApiClient
import com.example.onlineshopproject.databinding.ActivityDashboardBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var list: MutableList<DashboardModel>
    lateinit var apiinterface: ApiInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar!!.title = "Home"

        sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)
        Toast.makeText(
            applicationContext,
            "welcome" + sharedPreferences.getString("mob", ""),
            Toast.LENGTH_SHORT
        ).show()
        list = ArrayList()

        var layout: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recycler.layoutManager = layout

        apiinterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)
        var call: Call<List<DashboardModel>> = apiinterface.viewdata()
        call.enqueue(object : Callback<List<DashboardModel>> {
            override fun onResponse(
                call: Call<List<DashboardModel>>,
                response: Response<List<DashboardModel>>
            ) {

                list = response.body() as MutableList<DashboardModel>

                var myAdapter = DashboardAdapter(applicationContext, list)
                binding.recycler.adapter = myAdapter


            }

            override fun onFailure(call: Call<List<DashboardModel>>, t: Throwable) {

                Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.wish -> {
                startActivity(Intent(applicationContext, WishlistActivity::class.java))
            }

            R.id.cart -> {
                startActivity(Intent(applicationContext, CartActivity::class.java))
            }

            R.id.i1 -> {
                sharedPreferences.edit().clear().commit()
                finish()
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() {
        finishAffinity()
    }
}
