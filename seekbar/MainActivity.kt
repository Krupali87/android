package com.example.seekbar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    lateinit var img : ImageView
    lateinit var s1 : SeekBar
    lateinit var s2 : SeekBar
    lateinit var s3 : SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img = findViewById(R.id.img)
        s1 = findViewById(R.id.s1)
        s2 = findViewById(R.id.s2)
        s3 = findViewById(R.id.s3)

        s1.setOnSeekBarChangeListener(this)
        s2.setOnSeekBarChangeListener(this)
        s3.setOnSeekBarChangeListener(this)



    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

        var r = s1.progress
        var g = s2.progress
        var b = s3.progress

        img.setBackgroundColor(Color.rgb(r,g,b))

    }

    override fun onStartTrackingTouch(p0: SeekBar?) {


    }

    override fun onStopTrackingTouch(p0: SeekBar?) {


    }
}