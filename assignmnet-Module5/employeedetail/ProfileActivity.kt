package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.databinding.ActivityProfileBinding
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONException

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    lateinit var list: MutableList<employeemodel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        list = ArrayList()

        val stringRequest = StringRequest(Request.Method.POST,
            "https://krupalivaghela.000webhostapp.com/assignment/profileview.php",
            { response ->
                try {
                    val jsonArray = JSONArray(response)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val eid = jsonObject.getInt("e_id")
                        val ename = jsonObject.getString("e_name")
                        val eemail = jsonObject.getString("e_email")
                        val ephone = jsonObject.getString("e_phone")
                        val epassword = jsonObject.getString("e_password")

                        val m = employeemodel()
                        m.e_id = eid
                        m.e_name = ename
                        m.e_email = eemail
                        m.e_phone = ephone
                        m.e_password = epassword
                        list.add(m)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            })
        val queue = Volley.newRequestQueue(applicationContext)
        queue.add(stringRequest)

        val stringRequest1 = StringRequest(
            Request.Method.POST,
            "https://krupalivaghela.000webhostapp.com/assignment/imageview.php",
            { response ->
                try {
                    val jsonArray = JSONArray(response)
                    val jsonObject = jsonArray.getJSONObject(0)
                    val url = jsonObject.getString("url")
                    Picasso.get().load(url).into(binding.img)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            })
        val queue1 = Volley.newRequestQueue(applicationContext)
        queue1.add(stringRequest1)

        binding.btnupload.setOnClickListener {

            startActivity(Intent(applicationContext, UploadImageActivity::class.java))

        }

        binding.btnupload.setOnClickListener {

            val dialog = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_design, null)
            dialog.setView(dialogView)
            val edtname: TextInputEditText = dialogView.findViewById(R.id.dialogedtname)
            val edtemail: TextInputEditText = dialogView.findViewById(R.id.dialogedtemail)
            val edtphone: TextInputEditText = dialogView.findViewById(R.id.dialogedtphone)
            val edtpass: TextInputEditText = dialogView.findViewById(R.id.dialogedtpassword)
            val btnupdate: Button = dialogView.findViewById(R.id.dialogbtnupload)

            btnupdate.setOnClickListener {

                val ename = edtname.text.toString()
                val eemail = edtemail.text.toString()
                val ephone = edtphone.text.toString()
                val epassword = edtpass.text.toString()

                val dialogStringRequest = object : StringRequest(Request.Method.POST,
                    "https://krupalivaghela.000webhostapp.com/assignment/employeeupdate.php",
                    Response.Listener {

                        try {
                            Toast.makeText(applicationContext, "Updated", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext, ProfileActivity::class.java))
                            finish()
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    },
                    Response.ErrorListener {
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                    }) {
                    override fun getParams(): MutableMap<String, String>? {
                        val map = HashMap<String, String>()
                        map["e_id"] = list[0].e_id.toString()
                        map["e_name"] = ename
                        map["e_email"] = eemail
                        map["e_phone"] = ephone
                        map["e_password"] = epassword
                        return map
                    }
                }
                val queue2 = Volley.newRequestQueue(applicationContext)
                queue2.add(dialogStringRequest)

            }
            dialog.show()

        }
    }
}