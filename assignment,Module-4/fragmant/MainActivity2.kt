
package com.example.fragmenta


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class MainActivity2 : AppCompatActivity()
{

    lateinit var b1 : Button
    lateinit var b2 : Button
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)

        b1.setOnClickListener {

            var l1 = LoginFragment()
            var fm:FragmentManager = supportFragmentManager
            var ft: FragmentTransaction = fm.beginTransaction()
            ft.replace(R.id.frame,l1).commit()
        }
        b2.setOnClickListener {

                var r1 = RegistrationtFragment()
            var fm : FragmentManager = supportFragmentManager
            var ft:FragmentTransaction = fm.beginTransaction()
            ft.replace(R.id.frame,r1).commit()

        }
    }
}