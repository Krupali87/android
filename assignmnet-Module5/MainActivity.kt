package com.example.todoapp2

import android.content.AbstractThreadedSyncAdapter
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp2.Adapter.ToDoAdapter
import com.example.todoapp2.Database.DatabaseHelper
import com.example.todoapp2.Model.todoModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(),OnDialogCloseListener
{
    lateinit var recycler: RecyclerView
    lateinit var floating : FloatingActionButton
    lateinit var dbHelper: DatabaseHelper
    lateinit var list: MutableList<todoModel>
    lateinit var adapter: ToDoAdapter
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recycler)
        floating = findViewById(com.google.android.material.R.id.floating)
        dbHelper = DatabaseHelper(this)
        list = ArrayList<todoModel>()
        adapter = ToDoAdapter(dbHelper,this)
        list = dbHelper.getAllTasks()
        list.reverse()
        adapter.setTask(list)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        floating.setOnClickListener {

        addnewtask.newInstance().show(supportFragmentManager,addnewtask.TAG)
        }

        var itemTouchHelper = ItemTouchHelper(RecyclerViewTouchHelper(adapter))
        itemTouchHelper.attachToRecyclerView(recycler)


    }

    override fun onDialogClose(dialogInterface: DialogInterface)
    {
        list = dbHelper.getAllTasks()
        list.reverse()
        adapter.setTask(list)
        adapter.notifyDataSetChanged()
    }
}