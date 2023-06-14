package com.example.texttospeeach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.QUEUE_ADD
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    lateinit var e1 : EditText
    lateinit var b1 : Button
    lateinit var tts : TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        e1 = findViewById(R.id.e1)
        b1 = findViewById(R.id.b1)
        tts = TextToSpeech(applicationContext,this)

        b1.setOnClickListener {

            tts.setLanguage(Locale.UK)
            tts.setPitch(0.11F)
            tts.setSpeechRate(0.11F)

            var date = e1.text.toString()
            tts.speak(date,QUEUE_ADD,null)

        }
    }

    override fun onInit(p0: Int) {

        if (p0 == TextToSpeech.SUCCESS)
        {
            Toast.makeText(applicationContext,"Sucess",Toast.LENGTH_LONG).show()
        }
        else if (p0== TextToSpeech.LANG_MISSING_DATA)
        {
            Toast.makeText(applicationContext,"Missing Data",Toast.LENGTH_LONG).show()
        }
        else
        {
            Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
        }
    }
}