package com.example.hidetext

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity()
{

    lateinit var t1 : TextView
    lateinit var c1 : CheckBox
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        t1 = findViewById(R.id.t1)
        c1 = findViewById(R.id.c1)

        c1.setOnClickListener {

           if (c1.isChecked)
           {
                t1.isVisible = true
           }
            else
           {
               t1.isVisible = false
           }


        }


    }
}

