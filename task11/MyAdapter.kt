package com.example.task11

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyAdapter(var context: Context, fm : FragmentManager,var totalTabs: Int) : FragmentPagerAdapter(fm)
{
    override fun getCount(): Int {

        return totalTabs
    }

    override fun getItem(position: Int): Fragment {

        return when (position)
        {
            0 -> {
                OneFragment()
            }
            1 ->
                TwoFragment()
            2 ->
                ThreeFragment()

            else -> {
                getItem(position)
            }
        }
    }

}