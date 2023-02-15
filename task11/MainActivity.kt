package com.example.task11

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity()
{
    lateinit var toolbar: Toolbar
    lateinit var tabLayout: TabLayout
    lateinit var viewPager : ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.tool)
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

        viewPager = findViewById(R.id.view)
        setupViewpager()

        tabLayout = findViewById(R.id.tab)
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.addTab(tabLayout.newTab().setText("One").setIcon(R.drawable.ic_baseline_heart))
        tabLayout.addTab(tabLayout.newTab().setText("Two").setIcon(R.drawable.ic_baseline_call))
        tabLayout.addTab(tabLayout.newTab().setText("Three").setIcon(R.drawable.ic_baseline_person))

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = MyAdapter(this,supportFragmentManager,tabLayout.tabCount)



    }
    private fun setupViewpager()
    {

    }
}