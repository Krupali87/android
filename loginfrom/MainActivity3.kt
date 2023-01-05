package com.example.restaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.ActionMode

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        Handler().postDelayed(Runnable {
            var i = Intent(applicationContext,MainActivity::class.java)
            startActivity(i)
        },3000)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        finishAffinity()
    }

    }





