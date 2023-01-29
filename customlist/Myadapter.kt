package com.example.customlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text


class Myadapter(var context: Context,var list : MutableList<Model>) : BaseAdapter()
{
    override fun getCount(): Int
    {
        return list.size

    }

    override fun getItem(position: Int): Any
    {

        return position
    }

    override fun getItemId(position: Int): Long
    {

            return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
    {

            var layout: LayoutInflater = LayoutInflater.from(context)
            var view = layout.inflate(R.layout.design,parent,false)
            var image :ImageView = view.findViewById(R.id.img)
            var t1 : TextView = view.findViewById(R.id.t1)
            var t2 : TextView = view.findViewById(R.id.t2)

            image.setImageResource(list.get(position).image)
            t1.setText(list.get(position).name)
            t2.setText(list.get(position).price)
            return view
    }

}