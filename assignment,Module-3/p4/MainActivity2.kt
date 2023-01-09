package com.example.p4

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity()
{lateinit var textView: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    textView=findViewById(R.id.t1)
        var i = intent
        var data=   i.getStringExtra("msg")
        textView.setText(data)


    }

    override fun onBackPressed() {
        //super.onBackPressed()
        finishAffinity()
    }
}