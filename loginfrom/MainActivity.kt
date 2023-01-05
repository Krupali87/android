package com.example.loginfrom

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

            var name = e1.text.toString()
            var pass = e2.text.toString()
            if (name.length==0 && pass.length==0)
            {
                e1.setError("Please enter your name")
                e2.setError("Please enter your password")
            }
            else if (name.length==0)
            {
                e1.setError("Please enter your name")
            }
            else if (pass.length==0)
            {
                e2.setError("Please enter your password")
            }
            else
            {
                if (name.equals("krupali") && pass.equals("1234"))
                {
                    Toast.makeText(applicationContext,"success",Toast.LENGTH_LONG).show()
                    var i = Intent(applicationContext,MainActivity2::class.java)
                    i.putExtra("n1",name)
                    startActivity(i)
                }
                else
                {
                    Toast.makeText(applicationContext,"Fail",Toast.LENGTH_LONG).show()
                }

            }


        }
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        finishAffinity()
    }
}