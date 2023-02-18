package com.example.showhide

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity()
{
    lateinit var t1 : TextView
    lateinit var b1 : Button
    lateinit var b2 : Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        t1 = findViewById(R.id.t1)
        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)

        b1.setOnClickListener {

           t1.isVisible = true

        }
        b2.setOnClickListener{

                  t1.isVisible = false

        }

    }


}