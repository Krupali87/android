package com.example.onlineshopproject.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.onlineshopproject.Fragment.sliderFragment

class sliderAdapter(fm : FragmentManager,behavior:Int) : FragmentPagerAdapter(fm,behavior)
{
    override fun getCount(): Int {

        return 3
    }

    override fun getItem(position: Int): Fragment
    {
            return sliderFragment.newInstance(position)

    }
}