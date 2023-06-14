package com.example.wakelock

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.os.PowerManager
import android.os.PowerManager.WakeLock
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity()
{
    lateinit var video : VideoView
    companion object{
        var wakeLock: WakeLock? = null
    }

    @SuppressLint("InvalidWakeLockTag", "WakelockTimeout")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pm = getSystemService(POWER_SERVICE) as PowerManager
        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "DoNotSleep")
        wakeLock!!.acquire()

        video = findViewById(R.id.video)

        try {
            val mediaController = MediaController(this)
            mediaController.setAnchorView(video)
            video.setMediaController(mediaController)
            video.setVideoURI(
                Uri.parse("android.resource://" + packageName + "/" + R.raw.tom)
            )
            video.start()
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
        }




    }

    override fun onDestroy()
    {
        wakeLock!!.release()
        super.onDestroy()
    }
}