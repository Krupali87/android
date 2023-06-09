package com.example.selectepic


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.selectepic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnlogin.setOnClickListener {

            var user = binding.edtusername.text.toString()
            var pass = binding.edtpass.text.toString()

            if (user.length==0 && pass.length==0 )
            {
                binding.layoutedtname.error = "Please Enter username"
                binding.layoutedtpass.error = "Please Enter Password"

            }
            else if (user.length==0)
            {
                binding.layoutedtname.error = "Please Enter username"
            }
            else if (pass.length==0 )
            {
                binding.layoutedtpass.error = "Please Enter Password"
            }
            else
            {
                if (user.equals("welcome") && pass.equals("1234"))
                {
                    Toast.makeText(applicationContext,"welcome to gallery",Toast.LENGTH_LONG).show()
                    startActivity(Intent(applicationContext,MainActivity2::class.java))
                }
            }

        }

    }
}