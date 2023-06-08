package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.databinding.ActivityUpdateBinding
import org.json.JSONException

class UpdateActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val edtpName = binding.edtuname
        val edtpdetail = binding.edtudetail
        val intent = intent
        val pName = intent.getStringExtra("pName")
        val pDetails = intent.getStringExtra("pDetails")
        val pId = intent.getStringExtra("pId")
        edtpName.setText(pName)
        edtpdetail.setText(pDetails)

        binding.ufloating.setOnClickListener {

            val upname = edtpName.text.toString()
            val updetail = edtpdetail.text.toString()

            val stringRequest = object : StringRequest(
                Method.POST,
                "https://krupalivaghela.000webhostapp.com/assignment/projectupdate.php",
                Response.Listener {
                    try {
                        Toast.makeText(applicationContext, "Project updated", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(applicationContext, viewActivity::class.java))
                        finish()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }) {
                override fun getParams(): MutableMap<String, String> {
                    val map = HashMap<String, String>()
                    map["p_name"] =upname
                    map["p_detail"] = updetail
                    map["p_id"] = pId!!.toInt().toString()
                    return map
                }
            }
            val queue = Volley.newRequestQueue(this)
            queue.add(stringRequest)
        }
    }
        }

