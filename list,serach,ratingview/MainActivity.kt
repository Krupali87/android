package com.example.l1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ListView
import android.widget.RatingBar
import android.widget.SearchView
import android.widget.Toast

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    RatingBar.OnRatingBarChangeListener {

    lateinit var listView: ListView
    lateinit var list: MutableList<String>
    lateinit var SearchView: SearchView
    lateinit var arrayAdapter:ArrayAdapter<String>
    lateinit var arrayAdapter2: ArrayAdapter<String>
    lateinit var ratingBar: RatingBar
    lateinit var auto1: AutoCompleteTextView
    var city = arrayOf("rajkot","gondal","surat","morbi","baroda")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.list)
        SearchView = findViewById(R.id.search)
        list = ArrayList()
        list.add("Java")
        list.add("Android")
        list.add("Flutter")
        ratingBar = findViewById(R.id.rating)
        auto1 = findViewById(R.id.a1)
        auto1.threshold=1

        arrayAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, list)
        listView.adapter = arrayAdapter

        arrayAdapter2 = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,city)
        auto1.setAdapter(arrayAdapter2)

        SearchView.setOnQueryTextListener(this)
        ratingBar.setOnRatingBarChangeListener(this)



    }
    override fun onQueryTextSubmit(p0: String?): Boolean {

        /*if(list.contains(p0))
         {
             arrayAdapter.filter.filter(p0)
         }*/
        return  false
    }

    override fun onQueryTextChange(p0: String?): Boolean
    {
        arrayAdapter.filter.filter(p0)

        return  false
    }

    override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {

        var data= p0!!.rating
        Toast.makeText(applicationContext,""+data,Toast.LENGTH_LONG).show()

    }


}
