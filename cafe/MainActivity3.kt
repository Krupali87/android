package com.example.cafe

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity3 : AppCompatActivity() {

    lateinit var txt3: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        txt3 = findViewById(R.id.txt3)
        var i = intent
        txt3.setText(i.getStringExtra("Bill"))

    }

    override fun onBackPressed() {
        //super.onBackPressed()
        var alert = android.app.AlertDialog.Builder(this)
        alert.setTitle("Are you sure want to exit")
        alert.setPositiveButton("YES",
            { dialogInterface: DialogInterface, i: Int -> finishAffinity() })
        alert.setNegativeButton("NO",
            { dialogInterface: DialogInterface, i: Int -> dialogInterface.cancel() })
        alert.show()


    }
}