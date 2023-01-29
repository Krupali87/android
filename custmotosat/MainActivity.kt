package com.example.customtosatanddialog

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity()
{

    lateinit var b1 : Button
    lateinit var b2 : Button
    lateinit var mediaPlayer: MediaPlayer
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)
        mediaPlayer = MediaPlayer.create(applicationContext,R.raw.woh)

        b1.setOnClickListener {

            var toast = Toast(this)
            var layoutInflater : LayoutInflater = LayoutInflater.from(applicationContext)
            var view = layoutInflater.inflate(R.layout.design,null)
            toast.view = view
            toast.duration = Toast.LENGTH_LONG
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()

        }
        b2.setOnClickListener {

            var alerts = AlertDialog.Builder(this)
            var layoutInflater : LayoutInflater = LayoutInflater.from(applicationContext)
            var view = layoutInflater.inflate(R.layout.design2,null)
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



