package com.example.rotateandblinkimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.rotateandblinkimage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button1.setOnClickListener {

           val edt = binding.editText1
            val img = binding.imageView1

            if (edt.text.isNotEmpty())
            {
                val anglerotate = (edt.text.toString() + "f").toFloat()
                img.rotation = anglerotate
            }
            else
            {
                Toast.makeText(applicationContext, "Field cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        binding.button2.setOnClickListener {

            val img = binding.imageView1
            val animation : Animation = AnimationUtils.loadAnimation(applicationContext,R.anim.blinking)
            img.startAnimation(animation)

        }
    }
}