package com.example.task9

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity()

{
    lateinit var b1 : Button
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b1 = findViewById(R.id.b1)
        mediaPlayer = MediaPlayer.create(applicationContext,R.raw.wrong)

        b1.setOnClickListener {

            var alerts = AlertDialog.Builder(this)
            var layout : LayoutInflater = LayoutInflater.from(applicationContext)
            var view = layoutInflater.inflate(R.layout.design, null)
            var s1 : Switch = view.findViewById(R.id.s1)
            s1.setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked)
                {
                    mediaPlayer.start()
                }
                else
                {
                    mediaPlayer.stop()
                }


            }
            alerts.setView(view)
            alerts.show()
        }
    }
}