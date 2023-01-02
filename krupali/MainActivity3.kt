package com.example.krupali

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity3 : AppCompatActivity() {

    //declaration
    lateinit var i1 :ImageView
    lateinit var i2 : ImageView
    lateinit var t1:TextView
    lateinit var t2:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        //Initialization
        i1 =  findViewById(R.id.img1)
        i2 =  findViewById(R.id.img2)
        t1 = findViewById(R.id.txt1)
        t2 = findViewById(R.id.txt2)


        t1.setOnClickListener {
            Toast.makeText(applicationContext,"welcome to india",Toast.LENGTH_LONG).show()
        }
        t2.setOnClickListener {
            Toast.makeText(applicationContext,"welcome to australia",Toast.LENGTH_LONG).show()
        }

        i1.setOnClickListener {

            var i = Intent(applicationContext, MainActivity4::class.java)
            startActivity(i)

        }
        i2.setOnClickListener {



            var i4 = Intent(applicationContext,MainActivity4::class.java)
            startActivity(i4)
        }

        }

    }
