package com.example.tablelayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class MainActivity : AppCompatActivity()
{
    lateinit var linearLayout: LinearLayout
    lateinit var tableLayout : TableLayout
    lateinit var tableRow: TableRow
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        linearLayout= LinearLayout(this)
        tableLayout = TableLayout(this)
        tableRow = TableRow(this)

        var t1 : TextView = TextView(this)
        t1.setText("Copy")

        var t2 : TextView = TextView(this)
        t2.setText("Ctrl + c")
        

        var width = 300
        var height = 300

        tableRow.addView(t1,width, height)
        tableRow.addView(t2,width, height)


        linearLayout.addView(tableLayout)
        linearLayout.addView(tableRow)
        setContentView(linearLayout)

    }
}