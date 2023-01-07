package com.example.cafe

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {

    lateinit var c1 : CheckBox
    lateinit var c2 : CheckBox
    lateinit var c3 : CheckBox
    lateinit var c4 : CheckBox
    lateinit var c5 : CheckBox
    lateinit var btn1 : Button
    lateinit var img1 : ImageView
    lateinit var txt2 : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        c1 = findViewById(R.id.c1)
        c2 = findViewById(R.id.c2)
        c3 = findViewById(R.id.c3)
        c4 = findViewById(R.id.c4)
        c5 = findViewById(R.id.c5)
        btn1 = findViewById(R.id.btn1)
        img1 = findViewById(R.id.img1)
        txt2 = findViewById(R.id.txt2)


        btn1.setOnClickListener {


        var amount = 0
        var builder = StringBuilder("\n Selected Item")
        if (c1.isChecked)
        {
            amount+=119
            builder.append("\n Chocolate shake @ Rs. 119")
        }
            if (c2.isChecked)
            {
                amount+=159
                builder.append("\n Waqffle @ Rs.159")
            }
            if (c3.isChecked)
            {
                amount+=70
                builder.append("\n Mocktails @ Rs. 110")
            }
            if (c4.isChecked)
            {
                amount+=110
                builder.append("\n Brownie @ Rs.110")
            }
            if (c5.isChecked)
            {
                amount+= 40
                builder.append("\n Sandwich @ Rs.40 ")
            }
            builder.append("\n Total:" +amount)
            var i = Intent(applicationContext,MainActivity3::class.java)
            i.putExtra("Bill",builder.toString())
            startActivity(i)
        }

        txt2.setOnClickListener {
            var num = "+ 9190162 11119"
            var i = Intent()
            i.data = Uri.parse("tel:$num")
            startActivity(i)
        }
    }



    }






