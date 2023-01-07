package com.example.checkboxradiobutton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity3 : AppCompatActivity() {

    lateinit var t1 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        t1 = findViewById(R.id.t1)
        var i =intent
        t1.setText(i.getStringExtra("Bill"))

    }

    override fun onBackPressed() {
        //super.onBackPressed()
        finishAffinity()
    }
}