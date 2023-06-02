package com.example.taskmanagement

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Collections
import java.util.Comparator
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var searchView: SearchView
    private lateinit var btnSort: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnSave: FloatingActionButton
    private lateinit var databaseHelper: DataBaseHealper
    private lateinit var mutableList: MutableList<Model>
    private lateinit var arrayList: ArrayList<Model>
    private lateinit var myAdapter: MyAdapter


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.txt1)
        searchView = findViewById(R.id.searchView)
        recyclerView = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        btnSave = findViewById(R.id.floatingActionButton)
        btnSort = findViewById(R.id.btnSort)
        mutableList = ArrayList()
        arrayList = ArrayList()
        databaseHelper = DataBaseHealper(applicationContext)

        mutableList = databaseHelper.viewTasks()
        myAdapter = MyAdapter(this, mutableList)
        recyclerView.adapter = myAdapter

        registerForContextMenu(recyclerView)

        // Switching MainActivity to CreateTaskActivity
        btnSave.setOnClickListener {
            startActivity(Intent(applicationContext, createtaskActivity::class.java))
            finish()
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return false
            }
        })

        btnSort.setOnClickListener {
            sortListByDate(mutableList)
            myAdapter = MyAdapter(this, mutableList)
            recyclerView.adapter = myAdapter
        }
    }

    private fun sortListByDate(mutableList: MutableList<Model>) {
        Collections.sort(mutableList, EventDetailSortByDate())
    }

    private fun filter(newText: String?) {
        val filteredlist: ArrayList<Model> = ArrayList()
        for (i: Model in mutableList) {
            if (i.taskname.toLowerCase().contains(newText!!.toLowerCase())) {
                filteredlist.add(i)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(applicationContext, "No data found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position: Int = -1
        try {
            position = myAdapter.getPosition()
        } catch (e: Exception) {
            Log.d(TAG, e.localizedMessage, e)
            return super.onContextItemSelected(item)
        }
        when(item.itemId) {
            R.id.completeAction -> {
                val myAdapter = MyAdapter(applicationContext, mutableList)
                myAdapter.updateTaskStatus(position)
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Are you sure you want to exit?")
        alertDialog.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
            finishAffinity()
        }
        alertDialog.setNegativeButton("NO") { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.dismiss()
        }
    }
}

class EventDetailSortByDate : Comparator<Model> {
    @Override
    override fun compare(recyclerViewModel1: Model,
                         recyclerViewModel2: Model): Int {
        val dateObject1: Date = stringToDate(recyclerViewModel1.taskdatetime)
        val dateObject2: Date = stringToDate(recyclerViewModel2.taskdatetime)
        val cal1 = Calendar.getInstance()
        cal1.time = dateObject1
        val cal2 = Calendar.getInstance()
        cal2.time = dateObject2
        val month1 = cal1.get(Calendar.MONTH)
        val month2 = cal2.get(Calendar.MONTH)
        return if (month1 < month2) {
            -1
        } else if (month1 == month2) {
            cal1.get(Calendar.DAY_OF_MONTH) - cal2.get(Calendar.DAY_OF_MONTH)
        } else {
            1
        }
    }

    private fun stringToDate(taskdatetime: String): Date {
        var returnDate = Date()

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        try {
            returnDate = dateFormat.parse(taskdatetime)!!
        } catch (e: ParseException) {
            val altDateFormat= SimpleDateFormat("yyyy-MM-dd")
            try {
                returnDate = altDateFormat.parse(taskdatetime)!!
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            e.printStackTrace()
        }
        return returnDate
    }
}