package com.example.stickynote


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stickynote.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity()
{
     lateinit var binding: ActivityMain2Binding
    lateinit var databaseHelper: DatabaseHelper
    lateinit var list: MutableList<Model>
    lateinit var adapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        databaseHelper = DatabaseHelper(this)
        list = ArrayList<Model>()
        adapter= MyAdapter(list,databaseHelper,this)
        list = databaseHelper.getAllStickyNotes()
        list.reverse()
        adapter.setStickyNote(list)
        binding.recycler.setHasFixedSize(true)
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter

        binding.addFloating.setOnClickListener {

            val  i = Intent(applicationContext,MainActivity::class.java)
            startActivity(i)
        }

        val itemTouchHelper = ItemTouchHelper(RecyclerViewTouchHelper(adapter))
        itemTouchHelper.attachToRecyclerView(binding.recycler)
    }


}