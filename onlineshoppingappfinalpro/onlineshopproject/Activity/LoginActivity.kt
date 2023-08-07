package com.example.onlineshopproject.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.onlineshopproject.Interface.ApiInterface
import com.example.onlineshopproject.Model.RegisterModel
import com.example.onlineshopproject.R
import com.example.onlineshopproject.client.ApiClient
import com.example.onlineshopproject.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity()

{
    private lateinit var binding: ActivityLoginBinding
    lateinit var apiinterface: ApiInterface
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar!!.title = "Login"

        sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean(
                "user_session",
                false
            ) && !sharedPreferences.getString("phone", "")!!.isEmpty()
        ) {
            startActivity(Intent(applicationContext, DashboardActivity::class.java))
            finish()
        }

        binding.btnsignin.setOnClickListener {

            var phone = binding.lgnedtphoneno.text.toString()
            var pass = binding.lgnedtpassword.text.toString()

            apiinterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)

            var call: Call<RegisterModel> = apiinterface.logindata(phone, pass)
            call.enqueue(object : Callback<RegisterModel> {
                override fun onResponse(
                    call: Call<RegisterModel>,
                    response: Response<RegisterModel>
                ) {

                    sharedPreferences.edit().putString("phone", phone).commit()
                    sharedPreferences.edit().putBoolean("user_session", true).commit()
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()
                    startActivity(Intent(applicationContext, DashboardActivity::class.java))
                }

                override fun onFailure(call: Call<RegisterModel>, t: Throwable) {
                    Toast.makeText(applicationContext, "Fail", Toast.LENGTH_LONG).show()

                }

            })

        }

        binding.txtsignup.setOnClickListener {

            startActivity(Intent(applicationContext, RegistrationActivity::class.java))
        }
    }

        override fun onBackPressed() {
            finishAffinity()
        }


    }
