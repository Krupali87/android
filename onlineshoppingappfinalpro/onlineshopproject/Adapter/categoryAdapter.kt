package com.example.onlineshopproject.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopproject.Activity.CategoryItemClickActivity
import com.example.onlineshopproject.Model.categoryModel
import com.example.onlineshopproject.R
import com.squareup.picasso.Picasso

class categoryAdapter(var context: Context, var list: MutableList<categoryModel>)  : RecyclerView.Adapter<MyView2>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView2 {
        var inflater = LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.category_design,parent,false)
        return MyView2(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyView2, position: Int) {
        Picasso.get().load(list.get(position).image).into(holder.imageView)
        holder.textView1.text = list[position].name
        holder.textView2.text = list[position].price
        holder.textView3.text = list[position].description

        holder.itemView.setOnClickListener {

            var intent = Intent(context,CategoryItemClickActivity::class.java)
            intent.putExtra("image",list[position].image)
            intent.putExtra("name",list[position].name)
            intent.putExtra("price",list[position].price)
            intent.putExtra("description",list[position].description)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
    }


class MyView2(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imageView : ImageView = itemView.findViewById(R.id.category_imageView)
    val textView1 : TextView = itemView.findViewById(R.id.category_name_textView)
    val textView2 : TextView = itemView.findViewById(R.id.category_price_textView)
    val textView3 : TextView = itemView.findViewById(R.id.category_des_textView)

}
