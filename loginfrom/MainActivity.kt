package com.example.restaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var e1 : EditText
    lateinit var e2 : EditText
    lateinit var b1 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        e1 = findViewById(R.id.e1)
        e2 = findViewById(R.id.e2)
        b1 = findViewById(R.id.b1)
        b1.setOnClickListener {

        var mob =e1.text.toString()
            var pass = e2.text.toString()
            if (mob.length==0 && pass.length==0)
            {
                e1.setError("Please enter the mobile number")
                e2.setError("Please enter the Password")
            }
            else if (mob.length==0)
            {
                e1.setError("Please enter the mobile number")
            }
            else if (pass.length==0)
            {
                e2.setError("Please enter the Password")
            }
            else
            {
                    if (mob.equals("85741236987") && pass.equals("0907"))
                    {
                        Toast.makeText(applicationContext,"welcome to Restaurant",Toast.LENGTH_LONG).show()
                        var i = Intent(applicationContext,MainActivity2::class.java)
                        startActivity(i)
                    }
            }
        }
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        finishAffinity()
    }
}