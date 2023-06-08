package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONException


class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("SESSION", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("SESSION", false) &&
            !sharedPreferences.getString("phone", "")!!.isEmpty())
        {
            startActivity(Intent(applicationContext, viewActivity::class.java))
            finish()
        }
        binding.btnlogin.setOnClickListener {
            val ePhone = binding.edtphone
            val ePassword = binding.edtpass

            if (validatePhone(ePhone) && validatePassword(ePassword)) {

                val edtphone1 = ePhone.text.toString()
                val edtpass2 = ePassword.text.toString()

                val stringRequest = object : StringRequest(
                    Method.POST,
                    "https://krupalivaghela.000webhostapp.com/assignment/login.php",
                    { response ->
                        try {
                            if (response == "0")
                            {
                                Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
                            } else
                            {
                                Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT).show()


                                val i = Intent(applicationContext, viewActivity::class.java)
                                val editor = sharedPreferences.edit()
                                editor.putBoolean("SESSION", true)
                                editor.putString("phone", edtphone1)
                                editor.commit()
                                startActivity(i)


                            }
                        } catch (e: JSONException) {
                            print(e)
                        }
                    },
                    {
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                    }) {
                    override fun getParams(): MutableMap<String, String> {
                        val map = HashMap<String, String>()
                        map["e_phone"] = edtphone1
                        map["e_password"] = edtpass2
                        return map
                    }
                }
                val queue = Volley.newRequestQueue(applicationContext)
                queue.add(stringRequest)
            }
        }
        binding.btnrest.setOnClickListener {


            Toast.makeText(applicationContext,"Clicked",Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext,RegisterActivity::class.java))
           // finish()
        }
    }

    private fun validatePhone(ePhone: TextInputEditText): Boolean {

        return if (ePhone.text.toString().isEmpty()) {
            binding.layoutedtphone.error = "Employee phone field must not be empty."
            false
        } else {
            true
        }
    }

    private fun validatePassword(ePassword: TextInputEditText): Boolean {

        return if (ePassword.text.toString().isEmpty()) {
            binding.layoutedtpass.error = "Employee password field must not be empty."
            false
        } else {
            true
        }
    }
}