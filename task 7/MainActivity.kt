package com.example.task7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity()
{


    lateinit var b1 : Button
    lateinit var b2 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)

        b1.setOnClickListener {

            var alerts = AlertDialog.Builder(this)
            var layout : LayoutInflater = LayoutInflater.from(applicationContext)
            var view =layoutInflater.inflate(R.layout.design,null)
            alerts.setView(view)
            alerts.show()
        }

        b2.setOnClickListener {

            var alerts = AlertDialog.Builder(this)
            var layout : LayoutInflater= LayoutInflater.from(applicationContext)
            var view = layoutInflater.inflate(R.layout.design1,null)
            alerts.setView(view)
            alerts.show()
        }


    }
}