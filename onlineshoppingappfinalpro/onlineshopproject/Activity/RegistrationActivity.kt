package com.example.onlineshopproject.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.onlineshopproject.Interface.ApiInterface
import com.example.onlineshopproject.client.ApiClient
import com.example.onlineshopproject.databinding.ActivityRegistrationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class RegistrationActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityRegistrationBinding
    lateinit var apiinterface: ApiInterface
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar!!.title = "Registration"

        binding.btnreg.setOnClickListener {

            var fullname = binding.regedtfullname.text.toString()
            var uname = binding.regedtusername.text.toString()
            var email = binding.regedtemail.text.toString()
            var phone = binding.regedtphoneno.text.toString()
            var pass = binding.regedtpassword.text.toString()

            if(pass.length==0)
            {
                Toast.makeText(applicationContext,"Please Enter Proper Password",Toast.LENGTH_LONG).show()
            }
            else
            {
                if(fullname.length==0 )
                {
                    binding.regedtfullname.setError("Please Enter Proper FirstName")
                }
                else if(uname.length==0 )
                {
                    binding.regedtusername.setError("Please Enter Proper LastName")
                }
                else if(email.length==0 )
                {
                    binding.regedtemail.setError("Please Enter Proper Email")
                }
                else if(phone.length!=10 )
                {
                    binding.regedtphoneno.setError("Please Enter Proper Phone Number")
                }
                else
                {
                    apiinterface= ApiClient.getapiclient()!!.create(ApiInterface::class.java)

                    var registercall: Call<Void> = apiinterface.registerdetail(fullname,uname,email,phone,pass)
                    registercall.enqueue(object: Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {

                            startActivity(Intent(applicationContext, LoginActivity::class.java))
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()
                        }
                    })


                }



            }




        }
        binding.txtlogin.setOnClickListener {

            startActivity(Intent(applicationContext,LoginActivity::class.java))
        }


    }


}


