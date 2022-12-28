package com.example.krupali

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity()
{
    //declaration
    lateinit var txt1 : TextView
    lateinit var txt2 : TextView
    lateinit var txt3 : TextView
    lateinit var bt1 : Button
    lateinit var i1 : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initilization
        txt1 = findViewById(R.id.t1)
        txt2 = findViewById(R.id.t2)
        txt3 = findViewById(R.id.t3)
        bt1 =  findViewById(R.id.b1)
        i1 = findViewById(R.id.img)

        bt1.setOnClickListener {
            Toast.makeText(applicationContext,"welcome to android",Toast.LENGTH_LONG).show()
            var i = Intent(applicationContext,MainActivity2::class.java)
            startActivity(i)
        }
    }
}