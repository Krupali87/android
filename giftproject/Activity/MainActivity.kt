package com.example.giftshopproject.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.giftshopproject.R

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Timer for Splash Screen
        Handler().postDelayed(Runnable {

            startActivity(Intent(applicationContext,sliderActivity::class.java))
        },5000)// Timing in Miliseconds
    }

    override fun onBackPressed()
    {
            finishAffinity()
    }
}