package com.example.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.Toast
import java.nio.file.Files.list
import java.util.Collections.list

class MainActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {


        lateinit var listView: ListView
    lateinit var list : MutableList<String>
    lateinit var b1 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.list)
        list = ArrayList()
        b1 = findViewById(R.id.b1)


        list.add("abc")
        list.add("xyz")
        list.add("pqr")

        var adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
        listView.adapter = adapter

        registerForContextMenu(listView)

        b1.setOnClickListener {

            var popup1 : PopupMenu = PopupMenu(this,b1)
            popup1.menuInflater.inflate(R.menu.popup,popup1.menu)
            popup1. setOnMenuItemClickListener(this)
            popup1.show()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.option,menu)

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.i1->
            {
                Toast.makeText(applicationContext,"cut",Toast.LENGTH_LONG).show()
            }
            R.id.i2->
            {
                Toast.makeText(applicationContext,"paste",Toast.LENGTH_LONG).show()
            }
            R.id.i3->
            {
                Toast.makeText(applicationContext,"copy",Toast.LENGTH_LONG).show()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?)
    {
        menuInflater.inflate(R.menu.context,menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {

        when(item!!.itemId)
        {
            R.id.pop->
            {
                Toast.makeText(applicationContext,"Movie called",Toast.LENGTH_LONG).show()
            }

        }
        return false
    }
}