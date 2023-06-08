package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.databinding.ActivityInsertBinding
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONException

class ProjectInsertActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityInsertBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnsave.setOnClickListener {

            val pname = binding.edtprojectname
            val pdetail = binding.edtprojectdetail

            if (ValidateProjectName(pname) && ValidateProjectDetail(pdetail))
            {
                val projectname = pname.text.toString()
                val projectdetail = pdetail.text.toString()

               val stringRequest = object : StringRequest(Request.Method.POST,"https://krupalivaghela.000webhostapp.com/assignment/projectinsert.php",Response.Listener {

                   try {
                       Toast.makeText(applicationContext, "Recored Inserted", Toast.LENGTH_SHORT)
                           .show()
                       startActivity(Intent(applicationContext, viewActivity::class.java))
                   } catch (e: JSONException) {
                       print(e)
                   }
               },
                  Response.ErrorListener {
                      Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                                 })
               {
                   override fun getParams(): MutableMap<String, String>?
                   {
                       val map = HashMap<String,String>()
                       map["p_name"] = projectname
                       map["p_detail"] = projectdetail
                       return map
                   }
               }
                val queue  = Volley.newRequestQueue(this)
                queue.add(stringRequest)
            }
        }

    }
    fun ValidateProjectName(pname : TextInputEditText) : Boolean
    {
        if (pname.text.toString().isEmpty())
        {
            binding.layoutedtproject.error = "Please Enter Project Name"
            return false
        }
        else
        {
            return true
        }
    }
    fun ValidateProjectDetail(pdetail : TextInputEditText) : Boolean
    {
        if (pdetail.text.toString().isEmpty())
        {
            binding.edtprojectdetail.error = "Please Enter Project Detail"
            return false
        }
        else
        {
            return true
        }
    }

}