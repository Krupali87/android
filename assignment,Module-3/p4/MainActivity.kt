package com.example.p4

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var e1 : EditText
    lateinit var b1 : Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        e1 = findViewById(R.id.e1)
        b1 = findViewById(R.id.b1)

        b1.setOnClickListener {


            var message = e1.text.toString()
            var i = Intent(applicationContext,MainActivity2::class.java)
            i.putExtra("msg",message)
            startActivity(i)

        }
    }
}