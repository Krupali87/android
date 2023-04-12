package com.example.edittext

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity()
{

    lateinit var edt1 : EditText
    lateinit var btn1 : Button
    lateinit var l1 : LinearLayout
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt1 = findViewById(R.id.edt1)
        btn1 = findViewById(R.id.btn1)
        l1 = findViewById(R.id.l1)

        btn1.setOnClickListener {

            if (edt1.getText().toString().length > 0)
            {
                try
                {
                    l1.removeAllViews()
                }
                catch (e: Throwable)
                {
                    e.printStackTrace()
                }
                val length: Int = edt1.getText().toString().toInt()
                for (i in 0 until length)
                {
                    val editText = EditText(applicationContext)
                    editText.id = i + 1
                    editText.layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                    editText.hint = "EditText " + (i + 1)
                    l1.addView(editText)
                }
            }

        }

        }
    }









