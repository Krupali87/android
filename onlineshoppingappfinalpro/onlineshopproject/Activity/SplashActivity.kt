package com.example.onlineshopproject.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.onlineshopproject.R

class SplashActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Timer for Splash Screen

        Handler().postDelayed(Runnable {

        startActivity(Intent(applicationContext,SliderActivity::class.java))
        },5000) // Timing in Millisecond
    }

    override fun onBackPressed() {


        finishAffinity()
    }
}