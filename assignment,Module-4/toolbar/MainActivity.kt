package com.example.toolbar

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var toolBar: Toolbar
    private lateinit var planets_array: Array<String>
    private lateinit var listView:ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        planets_array = resources.getStringArray(R.array.planets_array)
        toolBar = findViewById(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        val spinnerAdapter: SpinnerAdapter = ArrayAdapter.createFromResource(
            applicationContext,
            R.array.planets_array, com.google.android.material.R.layout.support_simple_spinner_dropdown_item
        )
        val navigationSpinner = Spinner(supportActionBar!!.themedContext)
        navigationSpinner.adapter = spinnerAdapter

        toolBar.addView(navigationSpinner, 0)

        navigationSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    applicationContext,
                    "Item selected : " + planets_array[position],
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        //associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = (menu!!.findItem(R.id.acttion_search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        val list = ArrayList<String>()

        list.add("Mercury")
        list.add("Venus")
        list.add("Earth")
        list.add("Mars")
        list.add("Saturn")
        list.add("Uranus")
        list.add("Neptune")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = adapter

        adapter.filter.filter(newText)
        return false
    }
}