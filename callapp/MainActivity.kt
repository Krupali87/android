package com.example.callapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var i1: ImageView
    lateinit var txt1 : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        i1 = findViewById(R.id.img1)
        txt1 = findViewById(R.id.t1)

        //Explicit
        i1.setOnClickListener {
            var i = Intent(applicationContext,MainActivity2::class.java)
            startActivity(i)
        }
        //Implicit
        txt1.setOnClickListener {
            var num = "+919427884911"
            var i = Intent()
            i.data = Uri.parse("tel:$num")
            startActivity(i)
        }
    }
}