package com.example.array

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView


class MainActivity : AppCompatActivity()
{
    lateinit var listview : ListView
    lateinit var list : Array<String>
    lateinit var arrayAdapter:ArrayAdapter<String>
    val DaysOfWeek = arrayOf("")

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listview = findViewById(R.id.list)
        list = resources.getStringArray(R.array.DaysOfWeek)

         arrayAdapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
        listview.adapter = arrayAdapter




    }




}
