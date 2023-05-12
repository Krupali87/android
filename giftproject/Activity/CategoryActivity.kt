package com.example.giftshopproject.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giftshopproject.Adapter.categoryAdapter
import com.example.giftshopproject.Interface.Apiinterface
import com.example.giftshopproject.Model.categoryModel
import com.example.giftshopproject.R
import com.example.giftshopproject.client.ApiClient
import com.example.giftshopproject.databinding.ActivityCategoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityCategoryBinding
    lateinit var list:MutableList<categoryModel>
    lateinit var apiinterface: Apiinterface
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var i = intent
        var pos = i.getIntExtra("pos",111)

        Toast.makeText(applicationContext,""+pos,Toast.LENGTH_LONG).show()

        list = ArrayList()

        var layout : RecyclerView.LayoutManager = LinearLayoutManager(this)
       binding.recycler.layoutManager = layout

        apiinterface = ApiClient.getapiclient()!!.create(Apiinterface::class.java)

        if(pos==0)
        {
            var call: Call<List<categoryModel>> =  apiinterface.souvenirsdata()
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
                var call : Call<List<categoryModel>> = apiinterface.clothandaccesssories()
            call.enqueue(object  : Callback<List<categoryModel>>{
                override fun onResponse(call: Call<List<categoryModel>>, response: Response<List<categoryModel>>
                ) {
                    list = response.body() as MutableList<categoryModel>

                    var myAdapter = categoryAdapter(applicationContext,list)
                    binding.recycler.adapter=myAdapter
                }

                override fun onFailure(call: Call<List<categoryModel>>, t: Throwable)
                {
                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                }

            })

        }
        if (pos==2)
        {
            var call : Call<List<categoryModel>> = apiinterface.toysandgames()
            call.enqueue(object  : Callback<List<categoryModel>>{
                override fun onResponse(call: Call<List<categoryModel>>, response: Response<List<categoryModel>>
                ) {
                    list = response.body() as MutableList<categoryModel>

                    var myAdapter = categoryAdapter(applicationContext,list)
                    binding.recycler.adapter=myAdapter
                }

                override fun onFailure(call: Call<List<categoryModel>>, t: Throwable)
                {
                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                }

            })
        }
        if (pos==3)
        {
            var call : Call<List<categoryModel>> = apiinterface.foodanddrink()
            call.enqueue(object  : Callback<List<categoryModel>>{
                override fun onResponse(call: Call<List<categoryModel>>, response: Response<List<categoryModel>>
                ) {
                    list = response.body() as MutableList<categoryModel>

                    var myAdapter = categoryAdapter(applicationContext,list)
                    binding.recycler.adapter=myAdapter
                }

                override fun onFailure(call: Call<List<categoryModel>>, t: Throwable)
                {
                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                }

            })
        }
        if (pos==4)
        {
            var call : Call<List<categoryModel>> = apiinterface.homeanddecorefurnish()
            call.enqueue(object  : Callback<List<categoryModel>>{
                override fun onResponse(call: Call<List<categoryModel>>, response: Response<List<categoryModel>>
                ) {
                    list = response.body() as MutableList<categoryModel>

                    var myAdapter = categoryAdapter(applicationContext,list)
                    binding.recycler.adapter=myAdapter
                }

                override fun onFailure(call: Call<List<categoryModel>>, t: Throwable)
                {
                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                }

            })
        }
        if (pos==5)
        {
            var call : Call<List<categoryModel>> = apiinterface.bookandstationery()
            call.enqueue(object  : Callback<List<categoryModel>>{
                override fun onResponse(call: Call<List<categoryModel>>, response: Response<List<categoryModel>>
                ) {
                    list = response.body() as MutableList<categoryModel>

                    var myAdapter = categoryAdapter(applicationContext,list)
                    binding.recycler.adapter=myAdapter
                }

                override fun onFailure(call: Call<List<categoryModel>>, t: Throwable)
                {
                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                }

            })
        }
        if (pos==6)
        {
            var call : Call<List<categoryModel>> = apiinterface.personalcareproduct()
            call.enqueue(object  : Callback<List<categoryModel>>{
                override fun onResponse(call: Call<List<categoryModel>>, response: Response<List<categoryModel>>
                ) {
                    list = response.body() as MutableList<categoryModel>

                    var myAdapter = categoryAdapter(applicationContext,list)
                    binding.recycler.adapter=myAdapter
                }

                override fun onFailure(call: Call<List<categoryModel>>, t: Throwable)
                {
                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                }

            })
        }
        if (pos==7)
        {
            var call : Call<List<categoryModel>> = apiinterface.specialoccessories()
            call.enqueue(object  : Callback<List<categoryModel>>{
                override fun onResponse(call: Call<List<categoryModel>>, response: Response<List<categoryModel>>
                ) {
                    list = response.body() as MutableList<categoryModel>

                    var myAdapter = categoryAdapter(applicationContext,list)
                    binding.recycler.adapter=myAdapter
                }

                override fun onFailure(call: Call<List<categoryModel>>, t: Throwable)
                {
                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                }

            })
        }
        if (pos==8)
        {
            var call : Call<List<categoryModel>> = apiinterface.flowersandplant()
            call.enqueue(object  : Callback<List<categoryModel>>{
                override fun onResponse(call: Call<List<categoryModel>>, response: Response<List<categoryModel>>
                ) {
                    list = response.body() as MutableList<categoryModel>

                    var myAdapter = categoryAdapter(applicationContext,list)
                    binding.recycler.adapter=myAdapter
                }

                override fun onFailure(call: Call<List<categoryModel>>, t: Throwable)
                {
                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                }

            })
        }



    }
}