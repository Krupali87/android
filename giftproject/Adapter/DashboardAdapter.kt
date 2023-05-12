package com.example.giftshopproject.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.giftshopproject.Activity.CategoryActivity
import com.example.giftshopproject.Model.DashboardModel
import com.example.giftshopproject.R
import com.squareup.picasso.Picasso

class DashboardAdapter(var context: Context,var list: MutableList<DashboardModel>) : RecyclerView.Adapter<MyView>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView
    {
        var inflate = LayoutInflater.from(context)
        var view  = inflate.inflate(R.layout.desboard_design,parent,false)
        return MyView(view)
    }

    override fun getItemCount(): Int
    {
        return list.size

    }

    override fun onBindViewHolder(holder: MyView, position: Int)
    {
            holder.txt1.setText(list.get(position).name)
            Picasso.get().load(list.get(position).url).into(holder.img1)
            holder.itemView.setOnClickListener {

                var i = Intent(context,CategoryActivity::class.java)
                i.putExtra("pos",position)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            }
    }

}
class MyView(itemView : View): RecyclerView.ViewHolder(itemView)
{
    var txt1:TextView = itemView.findViewById(R.id.dashboard_txt)
    var img1 : ImageView = itemView.findViewById(R.id.dashboard_img)
    val ib1 = itemView.findViewById<ImageButton>(R.id.ib1)
    val ib2 = itemView.findViewById<ImageButton>(R.id.ib2)
}