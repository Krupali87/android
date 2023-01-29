package com.example.reversenumber

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity()
{

    lateinit var e1 : EditText
    lateinit var t1 : TextView
    lateinit var t2 : TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        e1 = findViewById(R.id.e1)
        t1 = findViewById(R.id.t1)
        t2 = findViewById(R.id.t2)
        e1.setOnClickListener() {
            val c : StringBuffer = StringBuffer(e1.getText().toString())
            t2.setText(c.reverse())
        }





    }

}