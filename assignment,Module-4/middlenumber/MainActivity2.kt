package com.example.myapplication90

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity()
{
    lateinit var result: TextView
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

         result=findViewById(R.id.result)
        val number = intent.getSerializableExtra("key")

        result.text = number.toString()
    }
}