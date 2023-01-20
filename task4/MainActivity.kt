package com.example.task5

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var list: MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.l1)
        list = ArrayList()

        list.add("Java")
        list.add("Android")
        list.add("Php")
        list.add("c")

        var adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->

            if (position==0)
            {
                val  url = "https://www.w3schools.com/java/"
                var i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
            else if (position==1)
            {
                val url = "https://www.tutorialkart.com/kotlin-android-tutorial/"
                var i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
            else if (position==2)
            {
                val  url = "https://www.javatpoint.com/php-tutorial"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
            else if (position==3)
            {
                val url = "https://www.geeksforgeeks.org/c-programming-language/"
                var i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)

            }
        }



    }
}