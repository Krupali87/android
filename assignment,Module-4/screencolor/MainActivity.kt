package com.example.changecolor

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup



class MainActivity : AppCompatActivity() {

    lateinit var linear: LinearLayout
    lateinit var r1: RadioButton
    lateinit var r2: RadioButton
    lateinit var r3: RadioButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linear = findViewById(R.id.linear)
        r1 = findViewById(R.id.r1)
        r2 = findViewById(R.id.r2)
        r3 = findViewById(R.id.r3)

        r1.setOnClickListener {

            linear.setBackgroundColor(Color.YELLOW)

        }
        r2.setOnClickListener{

            linear.setBackgroundColor(Color.BLUE)


        }
        r3.setOnClickListener {

            linear.setBackgroundColor(Color.GRAY)

        }
    }
}
