package com.example.task14

import android.Manifest.permission.CALL_PHONE
import android.Manifest.permission.SEND_SMS
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity()
{

    lateinit var e1 : EditText
    lateinit var e2 : EditText
    lateinit var b1 : Button
    lateinit var b2 : Button
    lateinit var linearLayout: LinearLayout
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        e1 = findViewById(R.id.e1)
        e2 = findViewById(R.id.e2)
        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)
        linearLayout = findViewById(R.id.linear)

        b1.setOnClickListener {

            val intent = Intent().apply {

                action = Intent.ACTION_DIAL
                data = Uri.parse("tel:7405352712")
            }
            startActivity(intent)
        }

        b2.setOnClickListener {

            val intent = Intent().apply {

                action = Intent.ACTION_SENDTO
                data = Uri.parse("smsto:7405352712")
                putExtra("sms_body","text message")
            }
            if (intent.resolveActivity(packageManager)!=null)
            {
                startActivity(intent)
            }
        }

        if (checkSelfPermission(CALL_PHONE)!= PERMISSION_GRANTED && checkSelfPermission(SEND_SMS)!= PERMISSION_GRANTED)
        {
            requestPermissions(arrayOf(CALL_PHONE, SEND_SMS),100)
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {

        if (requestCode==100)
        {
            Snackbar.make(linearLayout,"Permission Granted",Snackbar.LENGTH_LONG).show()
        }
        else
        {
            Snackbar.make(linearLayout,"Permission Denied",Snackbar.LENGTH_LONG).show()
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}