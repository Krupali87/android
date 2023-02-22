package com.example.imageview

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity()
{
    lateinit var linearLayout: LinearLayout
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textView = findViewById<TextView>(R.id.txt1)
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER


        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.f,R.drawable.g1,R.drawable.m1,R.drawable.y1)
    }
}




