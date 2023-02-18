package com.example.fontsize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity()
{

    lateinit var t1 : TextView
    lateinit var b1 : Button
    lateinit var b2 : Button
    var ourFontSize = 14f
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        t1 = findViewById(R.id.t1)
        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)

        b1.setOnClickListener {

            ourFontSize  +=4f
            t1.setTextSize(TypedValue.COMPLEX_UNIT_SP,ourFontSize)
        }

        b2.setOnClickListener {

            ourFontSize  -=4f
            t1.setTextSize(TypedValue.COMPLEX_UNIT_SP,ourFontSize)
        }
    }
}