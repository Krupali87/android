package com.example.frameanimation

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private lateinit var button2: Button

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageview)
        button = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)


        val ad = AnimationDrawable()
        val image1 = resources.getDrawable(R.drawable.penguin, null)
        val image2 = resources.getDrawable(R.drawable.penguin1, null)
        val image3 = resources.getDrawable(R.drawable.penguin2, null)

        ad.addFrame(image1, 100)
        ad.addFrame(image2, 100)
        ad.addFrame(image3, 100)

        imageView.background = ad
        button.setOnClickListener {

            ad.start()
        }
        button2.setOnClickListener {

            ad.stop()
        }
    }
}