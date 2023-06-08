package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.databinding.ActivityRegisterBinding
import com.example.myapplication.databinding.ActivityViewBinding
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONArray
import org.json.JSONException

class viewActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityViewBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var list: MutableList<projectmodel>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        sharedPreferences = getSharedPreferences("SESSION",Context.MODE_PRIVATE)
        list = ArrayList()

        binding.recycler.layoutManager = LinearLayoutManager(this)
        list = ArrayList()

        binding.floating.setOnClickListener {

            startActivity(Intent(applicationContext, ProjectInsertActivity::class.java))

        }




        val stringRequest= StringRequest(Request.Method.POST,"https://krupalivaghela.000webhostapp.com/assignment/view.php",
            {
                response ->
                try {
                    val jsonArray = JSONArray(response)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val pid = jsonObject.getString("p_id")
                        val pname = jsonObject.getString("p_name")
                        val pdetail = jsonObject.getString("p_detail")
                        val m = projectmodel()
                        m.id = pid
                        m.p_name = pname
                        m.p_detail = pdetail
                        list.add(m)
                    }

                }
                catch (e: JSONException)
                {
                    print(e)
                }

                val adapter = projectAdapter(applicationContext, list)
                binding.recycler.adapter = adapter
            })
        {
            Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
        }
        val queue = Volley.newRequestQueue(applicationContext)
        queue.add(stringRequest)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.option_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.l1 ->
            {
                sharedPreferences.edit().clear().apply()
                startActivity(Intent(applicationContext,MainActivity::class.java))
            }
            R.id.m1 ->
            {
                startActivity(Intent(applicationContext,ProfileActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}