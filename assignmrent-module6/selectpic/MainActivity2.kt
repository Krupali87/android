package com.example.selectepic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity2 : AppCompatActivity()
{
    lateinit var img : ImageView
    lateinit var btnselect : Button

    companion object
    {
        val IMAGE_REQUEST_CODE = 100
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        img = findViewById(R.id.img)
        btnselect = findViewById(R.id.btnselect)

        btnselect.setOnClickListener {

            pickImageGallery()
        }
    }

    private fun pickImageGallery()
    {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode ==  IMAGE_REQUEST_CODE && resultCode ==  RESULT_OK)
        {
            img.setImageURI(data?.data)
        }
    }
}