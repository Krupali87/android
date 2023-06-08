package com.example.myapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.databinding.ActivityRegisterBinding
import com.google.android.material.textfield.TextInputEditText


class RegisterActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnreg.setOnClickListener {

            var name = binding.edtusername
            var email = binding.edtuseremail
            var phone = binding.edtuserphone
            var pass = binding.edtuserpass

            if (ValidateName(name)&& ValidateEmail(email) && Validatephone(phone) && Validatepass(pass))
            {
                val edtname = name.text.toString()
                val edtemail = email.text.toString()
                val edtphone = phone.text.toString()
                val edtpass = phone.text.toString()

                val stringRequest = object : StringRequest(
                    Method.POST,"https://krupalivaghela.000webhostapp.com/assignment/register.php",
                    Response.Listener {

                        Toast.makeText(applicationContext, "Registered", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, MainActivity::class.java))

                    },
                    Response.ErrorListener {

                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                    })
                {
                    override fun getParams(): MutableMap<String, String>?
                    {
                        val map = HashMap<String,String>()
                        map["e_name"] = edtname
                        map["e_email"] = edtemail
                        map["e_phone"] = edtphone
                        map["e_password"] = edtpass
                        return map
                    }
                }
                val queue = Volley.newRequestQueue(this)
                queue.add(stringRequest)
            }
        }




    }
    fun ValidateName(name : TextInputEditText) : Boolean
    {
        if (name.text.toString().trim().isEmpty())
        {
            binding.layoutedtname.error = "Name must be required"
            return false
        }
        else
        {
            return true
        }
    }
    fun ValidateEmail(email : TextInputEditText) : Boolean
    {
        if (email.text.toString().trim().isEmpty())
        {
            binding.layoutedtemail.error = "Email must required"
            return false
        }
        else
        {
            return true
        }
    }
    fun Validatephone(phone: TextInputEditText): Boolean {
        return if (phone.text.toString().isEmpty()) {
            binding.layoutedtphone.error = "Employee phone field must not be empty."
            false
        } else {
            true
        }
    }

    fun Validatepass(pass: TextInputEditText): Boolean {
        return if (pass.text.toString().isEmpty()) {
            binding.layoutedtpass.error = "Employee password field must not be empty."
            false
        } else {
            true
        }
    }


    }


