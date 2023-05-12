package com.example.giftshopproject.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import com.example.giftshopproject.Interface.Apiinterface
import com.example.giftshopproject.client.ApiClient
import com.example.giftshopproject.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class signupActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
    private lateinit var binding: ActivitySignupBinding
    lateinit var apiinterface: Apiinterface
    var gender1 = ""
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rbMale.setOnCheckedChangeListener(this)
        binding.rbFemale.setOnCheckedChangeListener(this)

        binding.btnsign.setOnClickListener {

            var fname = binding.e1fname.text.toString()
            var lname = binding.e2lname.text.toString()
            var email = binding.e3email.text.toString()
            var phone = binding.e4phone.text.toString()
            var pass = binding.e5pass.text.toString()
            var gender = gender1

            if (pass.length==0)
            {
                Toast.makeText(applicationContext, "Please enetr proper password", Toast.LENGTH_SHORT).show()
            }
            else
            {
                if (fname.length==0)
                {
                    binding.e1fname.setError("Please Enter proper FirstName")
                }
                if (lname.length==0)
                {
                    binding.e2lname.setError("Please Enter proper LastName")
                }
                if (email.length==0)
                {
                    binding.e3email.setError("Please Enter proper Email")
                }
                if (phone.length==0)
                {
                    binding.e4phone.setError("Please Enter proper Phone")
                }
                else
                {
                    apiinterface= ApiClient.getapiclient()!!.create(Apiinterface::class.java)

                    var registercall: Call<Void> = apiinterface.registerdetail(fname,lname,gender,email,phone,pass)
                    registercall.enqueue(object: Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {

                            startActivity(Intent(applicationContext, loginActivity::class.java))
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()
                        }
                    })

                }


            }
        }
        }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

        if (binding.rbMale.isChecked)
        {
            gender1 = "Male"
    }
        if (binding.rbFemale.isChecked)
        {
            gender1 = "Female"
        }


}
}