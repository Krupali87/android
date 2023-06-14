package com.example.audio

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity(), MediaPlayer.OnPreparedListener {
    lateinit var btn1 : Button
    lateinit var btn2 : Button
    lateinit var mediaPlayer: MediaPlayer
    var url = "https://krupalivaghela.000webhostapp.com/android_ringtone.mp3"
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)

        //play song from resources folder

       mediaPlayer = MediaPlayer.create(applicationContext,R.raw.android_ringtone)

        btn1.setOnClickListener {

            // playing music online using 000webhost
            mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource(url)
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener(this)
            mediaPlayer.start()

            // playing song from mobile
            var file = Environment.getExternalStorageDirectory()
            file = File(file, "/storage/emulated/0/Music/song.mp3")
            if (file.exists())
            {
                mediaPlayer = MediaPlayer()
                val uri: Uri = Uri.fromFile(file)
                mediaPlayer = MediaPlayer.create(this, uri)
                mediaPlayer.start()
            }
        }
        btn2.setOnClickListener {

            mediaPlayer.stop()
        }



    }

    override fun onPrepared(mp: MediaPlayer?)
    {
            mediaPlayer.start()
    }
}