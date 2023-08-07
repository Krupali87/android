package com.example.onlineshopproject.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopproject.Model.DashboardModel
import com.example.onlineshopproject.R
import com.squareup.picasso.Picasso

class DashboardAdapter(var context: Context, var list: MutableList<DashboardModel>) : RecyclerView.Adapter<MyView>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        var inflate = LayoutInflater.from(context)
        var view  = inflate.inflate(R.layout.dashborad_design,parent,false)
        return MyView(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.txt1.setText(list.get(position).name)
        Picasso.get().load(list.get(position).url).into(holder.img1)
    }


}

class MyView(itemView : View) :  RecyclerView.ViewHolder(itemView)
{
    var txt1: TextView = itemView.findViewById(R.id.dashboard_txt)
    var img1 : ImageView = itemView.findViewById(R.id.dashboard_img)


}

