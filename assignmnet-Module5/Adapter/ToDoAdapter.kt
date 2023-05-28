package com.example.todoapp2.Adapter


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp2.Database.DatabaseHelper
import com.example.todoapp2.MainActivity
import com.example.todoapp2.Model.todoModel
import com.example.todoapp2.R
import com.example.todoapp2.addnewtask

class ToDoAdapter(databaseHelper: DatabaseHelper,activity: MainActivity) : RecyclerView.Adapter<MyViewHolder>()
{
    private lateinit var list: MutableList<todoModel>
   private lateinit var activity: MainActivity
   private lateinit var  dbHelper: DatabaseHelper

    init {

        this.activity = activity
        this.dbHelper = databaseHelper
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.task_layout,parent,false)
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int
    {
            return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        val item : todoModel = list[position]
        holder.chk.text = item.getTask()
        holder.chk.isChecked = toBoolean(item.getStatus())
        holder.chk.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked)
            {
                dbHelper.updateStatus(item.getId(),1)
            }
            else
            {
                dbHelper.updateStatus(item.getId(),0)
            }
        }
    }
    fun getContext() : Context
    {
            return activity
    }

    fun setTask(list: MutableList<todoModel>)
    {
        this.list = list
        notifyDataSetChanged()
    }


    fun deleteTask(position: Int)
    {
        val item : todoModel = list[position]
        dbHelper.deleteTask(item.getId())
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun editTask(position: Int)
    {
        val item : todoModel = list[position]

        val bundle = Bundle()

        bundle.putInt("id",item.getId())
        bundle.putString("task",item.getTask())

        val task = addnewtask()
        task.arguments = bundle
        task.show(activity.supportFragmentManager,task.tag)
    }

    private fun toBoolean(i: Int): Boolean
    {
        return i!=0
    }

}
class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
{
    var chk : CheckBox
    init {

        chk = itemView.findViewById(R.id.chk)
    }
}