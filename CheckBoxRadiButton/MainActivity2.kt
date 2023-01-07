package com.example.checkboxradiobutton

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox

class MainActivity2 : AppCompatActivity() {

    lateinit var c1 : CheckBox
    lateinit var c2 : CheckBox
    lateinit var c3 : CheckBox
    lateinit var b1 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        c1 = findViewById(R.id.c1)
        c2 = findViewById(R.id.c2)
        c3 = findViewById(R.id.c3)
        b1 = findViewById(R.id.b1)

        b1.setOnClickListener {

            var amount = 0
            var builder = StringBuilder(" \n Selected Items ")

            if (c1.isChecked)
            {
                amount+=100
                builder.append(" \n PIZZA @ Rs. 100 ")

            }
            if (c2.isChecked)
            {
                amount+=150
                builder.append("\n  PASTA @ Rs. 150")

            }
            if (c3.isChecked)
            {
                amount+= 70
                builder.append(" \n BURGER @ Rs. 70")

            }
            builder.append(" \n Total :"+amount)
            var i = Intent(applicationContext,MainActivity3::class.java)
            i.putExtra("Bill",builder.toString())
            startActivity(i)

        }
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        var alert = AlertDialog.Builder(this)
        alert.setTitle("Are you sure want to exit")
        alert.setPositiveButton("YES",{ dialogInterface: DialogInterface, i: Int -> finishAffinity() })
        alert.setNegativeButton("NO",{ dialogInterface: DialogInterface, i: Int -> dialogInterface.cancel()})
        alert.show()

    }
}