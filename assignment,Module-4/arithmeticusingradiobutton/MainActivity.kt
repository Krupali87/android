package com.example.oprationusingradiobutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView

class MainActivity : AppCompatActivity()
{

    lateinit var e1 : EditText
    lateinit var e2 : EditText
    lateinit var r1 : RadioButton
    lateinit var r2 : RadioButton
    lateinit var r3 : RadioButton
    lateinit var r4 : RadioButton
    lateinit var ans : TextView
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        e1 = findViewById(R.id.e1)
        e2 = findViewById(R.id.e2)
        r1 = findViewById(R.id.r1)
        r2 = findViewById(R.id.r2)
        r3 = findViewById(R.id.r3)
        r4 = findViewById(R.id.r4)
        ans = findViewById(R.id.ans)

        r1.setOnClickListener {

            var n1 = Integer.parseInt(e1.getText().toString())
            var n2 = Integer.parseInt(e2.getText().toString())

            var add = n1 + n2
            ans.setText("ANSWER = "+add)
        }
        r2.setOnClickListener {

            var n1 = Integer.parseInt(e1.getText().toString())
            var n2 = Integer.parseInt(e2.getText().toString())

            var sub = n1 - n2
            ans.setText("ANSWER = "+sub)
        }
        r3.setOnClickListener {

            var n1 = Integer.parseInt(e1.getText().toString())
            var n2 = Integer.parseInt(e2.getText().toString())

            var multi = n1 * n2
            ans.setText("ANSWER = "+multi)
        }
        r4.setOnClickListener {

            var n1 = Integer.parseInt(e1.getText().toString())
            var n2 = Integer.parseInt(e2.getText().toString())

            var div = n1 + n2
            ans.setText("ANSWER = "+div)
        }



        }
    }
