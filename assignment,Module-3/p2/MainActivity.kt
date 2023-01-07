package com.example.p2

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var txt1 : TextView
    lateinit var btn1 : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt1 = findViewById(R.id.txt1)
        btn1 = findViewById(R.id.btn1)

        btn1.setOnClickListener {

            btn1.setBackgroundColor(Color.DKGRAY)
        }
    }
}