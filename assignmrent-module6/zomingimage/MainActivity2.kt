package com.example.movingimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.view.animation.AnimationUtils

class MainActivity2 : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val buttonZoomIn: Button = findViewById(R.id.zoomInButton)
        val buttonZoomOut: Button = findViewById(R.id.zoomOutButton)
        val image: ImageView = findViewById(R.id.imageView)

        buttonZoomIn.setOnClickListener()
        {
            val animZoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
            image.startAnimation(animZoomIn)
        }
        buttonZoomOut.setOnClickListener()
        {
            val animZoomOut = AnimationUtils.loadAnimation(this, R.anim.zoom_out)
            image.startAnimation(animZoomOut)
        }
    }
}