package com.example.cafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var e1: EditText
    lateinit var e2: EditText
    lateinit var b1: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        e1 = findViewById(R.id.e1)
        e2 = findViewById(R.id.e2)
        b1 = findViewById(R.id.b1)
        b1.setOnClickListener {

            var user = e1.text.toString()
            var pass = e2.text.toString()

            if (user.length == 0 && pass.length == 0)
            {
                e1.setError("Please Enter the username")
                e2.setError("Please enter the password")

            }
            else if (user.length == 0)
            {
                e1.setError("Please enter the username")
            }
            else if (pass.length == 0)
            {
                e2.setError("Please enter the password")
            } else
            {
                if (user.equals("cafe123") && pass.equals("8780"))
                {
                    Toast.makeText(applicationContext, "welcome to Cafe", Toast.LENGTH_LONG).show()
                    var i = Intent(applicationContext, MainActivity2::class.java)
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

