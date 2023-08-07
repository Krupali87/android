package com.example.onlineshopproject.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopproject.Adapter.categoryAdapter
import com.example.onlineshopproject.Interface.ApiInterface
import com.example.onlineshopproject.Model.categoryModel
import com.example.onlineshopproject.R
import com.example.onlineshopproject.client.ApiClient
import com.example.onlineshopproject.databinding.ActivityCategoryItemsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryItemsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryItemsBinding
    lateinit var list:MutableList<categoryModel>
    lateinit var apiinterface: ApiInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryItemsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar!!.title = "Show Category"

        var i = intent
        var pos = i.getIntExtra("pos",111)

        Toast.makeText(applicationContext,""+pos,Toast.LENGTH_LONG).show()

        list = ArrayList()

        var layout : RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recycler.layoutManager = layout

        apiinterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)

        if(pos==0)
        {
            var call: Call<List<categoryModel>> =  apiinterface.womendata()
            call.enqueue(object : Callback<List<categoryModel>> {
                override fun onResponse(call: Call<List<categoryModel>>, response: Response<List<categoryModel>>) {

                    list = response.body() as MutableList<categoryModel>

                    var myAdapter = categoryAdapter(applicationContext,list)
                    binding.recycler.adapter=myAdapter
                }
                override fun onFailure(call: Call<List<categoryModel>>, t: Throwable) {

                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                }
            })
        }

        if(pos==1)
        {
            var call: Call<List<categoryModel>> =  apiinterface.mendata()
            call.enqueue(object : Callback<List<categoryModel>> {
                override fun onResponse(call: Call<List<categoryModel>>, response: Response<List<categoryModel>>) {

                    list = response.body() as MutableList<categoryModel>

                    var myAdapter = categoryAdapter(applicationContext,list)
                    binding.recycler.adapter=myAdapter
                }
                override fun onFailure(call: Call<List<categoryModel>>, t: Throwable) {

                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                }
            })
        }

        if(pos==2)
        {
            var call: Call<List<categoryModel>> =  apiinterface.kiddata()
            call.enqueue(object : Callback<List<categoryModel>> {
                override fun onResponse(call: Call<List<categoryModel>>, response: Response<List<categoryModel>>) {

                    list = response.body() as MutableList<categoryModel>

                    var myAdapter = categoryAdapter(applicationContext,list)
                    binding.recycler.adapter=myAdapter
                }
                override fun onFailure(call: Call<List<categoryModel>>, t: Throwable) {

                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                }
            })
        }

        if(pos==3)
        {
            var call: Call<List<categoryModel>> =  apiinterface.grocerydata()
            call.enqueue(object : Callback<List<categoryModel>> {
                override fun onResponse(call: Call<List<categoryModel>>, response: Response<List<categoryModel>>) {

                    list = response.body() as MutableList<categoryModel>

                    var myAdapter = categoryAdapter(applicationContext,list)
                    binding.recycler.adapter=myAdapter
                }
                override fun onFailure(call: Call<List<categoryModel>>, t: Throwable) {

                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                }
            })
        }

        if(pos==4)
        {
            var call: Call<List<categoryModel>> =  apiinterface.electronicdata()
            call.enqueue(object : Callback<List<categoryModel>> {
                override fun onResponse(call: Call<List<categoryModel>>, response: Response<List<categoryModel>>) {

                    list = response.body() as MutableList<categoryModel>

                    var myAdapter = categoryAdapter(applicationContext,list)
                    binding.recycler.adapter=myAdapter
                }
                override fun onFailure(call: Call<List<categoryModel>>, t: Throwable) {

                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                }
            })
        }


    }
}