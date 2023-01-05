package com.example.restaurant

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {

    lateinit var t1: TextView
    lateinit var img1: ImageView
    lateinit var t2: TextView
    lateinit var t3: TextView
    lateinit var img2: ImageView
    lateinit var t4: TextView
    lateinit var t5: TextView
    lateinit var img3: ImageView
    lateinit var t6: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        t1 = findViewById(R.id.t1)
        img1 = findViewById(R.id.img1)
        t2 = findViewById(R.id.t2)
        t3 = findViewById(R.id.t3)
        img2 = findViewById(R.id.img2)
        t4 = findViewById(R.id.t4)
        t5 = findViewById(R.id.t5)
        img3 = findViewById(R.id.img3)
        t6 = findViewById(R.id.t6)

        t2.setOnClickListener {

            var num = "+919825516400"
            var i = Intent()
            i.data = Uri.parse("tel:$num")
            startActivity(i)
        }
        t4.setOnClickListener {

            var num = "+917433074335"
            var i = Intent()
            i.data = Uri.parse("tel:$num")
            startActivity(i)
        }
        t6.setOnClickListener {

            var num = "+919925742626"
            var i = Intent()
            i.data = Uri.parse("tel:$num")
            startActivity(i)

        }
    }

    override fun onBackPressed() {
       // super.onBackPressed()
        finishAffinity()
    }
}