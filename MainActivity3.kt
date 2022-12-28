package com.example.krupali

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity3 : AppCompatActivity() {

    //declaration
    lateinit var i1 :ImageView
    lateinit var i2 : ImageView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        //Initialization
        i1 =  findViewById(R.id.img1)
        i2 =  findViewById(R.id.img2)
        i1.setOnClickListener {
            var i = Intent(applicationContext, MainActivity4::class.java)
            startActivity(i)

        }
        i2.setOnClickListener {
            var i4 = Intent(applicationContext,MainActivity4::class.java)
            startActivity(i4)
        }

        }

    }
