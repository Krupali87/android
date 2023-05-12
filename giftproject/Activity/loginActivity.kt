package com.example.giftshopproject.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.giftshopproject.Interface.Apiinterface
import com.example.giftshopproject.Model.RegisterModel
import com.example.giftshopproject.client.ApiClient
import com.example.giftshopproject.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class loginActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityLoginBinding
    lateinit var apiinterface: Apiinterface
    lateinit var sharedPreferences: SharedPreferences
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences=getSharedPreferences("user_session", Context.MODE_PRIVATE)

            if (sharedPreferences.getBoolean("user_session",false) && !sharedPreferences.getString("mobile","")!!.isEmpty())
            {
                startActivity(Intent(applicationContext,DashboardActivity::class.java))
                finish()
            }
        binding.btnlogin.setOnClickListener {

            var phone = binding.edtphone.text.toString()
            var pass = binding.edtpass.text.toString()


            apiinterface= ApiClient.getapiclient()!!.create(Apiinterface::class.java)
            var call: Call<RegisterModel> = apiinterface.logindata(phone,pass)
            call.enqueue(object: Callback<RegisterModel> {
                override fun onResponse(
                    call: Call<RegisterModel>,
                    response: Response<RegisterModel>, )
                {
                    sharedPreferences.edit().putString("mobile",phone).commit()

                    sharedPreferences.edit().putBoolean("user_session",true).commit()
                    Toast.makeText(applicationContext,"Success", Toast.LENGTH_LONG).show()
                    startActivity(Intent(applicationContext, DashboardActivity::class.java))

                }

                override fun onFailure(call: Call<RegisterModel>, t: Throwable)
                {
                    Toast.makeText(applicationContext,"Fail", Toast.LENGTH_LONG).show()

                }
            })
        }
        binding.txt5.setOnClickListener {

            startActivity(Intent(applicationContext,signupActivity::class.java))

        }

    }

    override fun onBackPressed() {
        finishAffinity()
    }

        }



