package com.example.progressbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {

    lateinit var p1 : ProgressBar
    lateinit var b1 : Button
    lateinit var b2 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        p1 = findViewById(R.id.p1)
        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)
        b1.setOnClickListener {

            p1.incrementProgressBy(1)
            setProgress(200*p1.progress)
        }
        b2.setOnClickListener {

            p1.incrementProgressBy(-1)
            setProgress(200*p1.progress)
        }
    }
}