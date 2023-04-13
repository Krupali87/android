package com.example.myapplication90

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity()
{

     lateinit var e1: EditText
     lateinit var e2: EditText
     lateinit var btn1: Button
    override fun onCreate(savedInstanceState: Bundle?)

    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        e1 = findViewById(R.id.e1)
        e2 = findViewById(R.id.e2)
        btn1 = findViewById(R.id.btn1)

        btn1.setOnClickListener {
            var num1 = e1.text.toString().toInt()
            var num2 = e2.text.toString().toInt()

            val nums: ArrayList<Int> = ArrayList()

            if (num1 > num2)
            {
                while (num2<=num1) {
                    nums.add(num2)
                    num2++
                }
            } else {
                for (i in num1..num2) {
                    nums.add(num1)
                    num1++
                }
            }
            val intent = Intent(applicationContext, MainActivity2::class.java)
            intent.putExtra("key", nums)
            startActivity(intent)
        }
    }

}