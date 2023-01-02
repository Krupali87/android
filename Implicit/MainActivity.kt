package com.example.implicit

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var t3 : TextView
    lateinit var b4 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        t3 = findViewById(R.id.t1)
        b4 = findViewById(R.id.b1)
        b4.setOnClickListener {
            val url = "https://web.whatsapp.com/"
            var i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)

        }
    }
}





