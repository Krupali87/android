package com.example.stickynote

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var list: MutableList<Model>,var dbHelper: DatabaseHelper,var activity2 : MainActivity2) : RecyclerView.Adapter<ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
       var view = LayoutInflater.from(parent.context).inflate(R.layout.design,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int
    {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.textView.text = list.get(position).getStickyNote()
    }

    fun getContext() : Context
    {
        return activity2
    }
    fun setStickyNote(list: MutableList<Model>)
    {
        this.list = list
        notifyDataSetChanged()
    }
    fun deleteStickyNote(position: Int)
    {
            val item = list[position]
            dbHelper.deleteStickyNote(item.getId())
            list.removeAt(position)
            notifyItemRemoved(position)
    }
    fun editStickyNotes(position: Int)
    {
        val item = list[position]
        val i = Intent(getContext(),MainActivity3::class.java)
        i.putExtra("id",item.getId())
        i.putExtra("stickynote",item.getStickyNote())
        getContext().startActivity(i)
    }

    fun toBoolean(num: Int): Boolean {
        return num != 0
    }

}
class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
{
    var textView : TextView =itemView.findViewById(R.id.text_note)
}