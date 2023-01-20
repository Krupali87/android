package com.example.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(applicationContext,"On create",Toast.LENGTH_LONG).show()
    }

    override fun onStart() {

        Toast.makeText(applicationContext,"On Start",Toast.LENGTH_LONG).show()
        super.onStart()
    }

    override fun onResume() {

        Toast.makeText(applicationContext,"On Resume",Toast.LENGTH_LONG).show()
        super.onResume()
    }

    override fun onPause() {

        Toast.makeText(applicationContext,"On Pause",Toast.LENGTH_LONG).show()
        super.onPause()
    }

    override fun onStop() {

        Toast.makeText(applicationContext,"On Stop",Toast.LENGTH_LONG).show()
        super.onStop()
    }

    override fun onRestart() {

        Toast.makeText(applicationContext,"On Restart",Toast.LENGTH_LONG).show()
        super.onRestart()
    }

    override fun onDestroy() {

        Toast.makeText(applicationContext,"On Destroy",Toast.LENGTH_LONG).show()
        super.onDestroy()
    }
}