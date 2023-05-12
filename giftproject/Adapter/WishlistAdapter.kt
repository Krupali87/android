package com.example.giftshopproject.Adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.giftshopproject.Activity.WishlistActivity
import com.example.giftshopproject.Interface.Apiinterface
import com.example.giftshopproject.Model.WishlistModel
import com.example.giftshopproject.R
import com.example.giftshopproject.client.ApiClient
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WishlistAdapter(var context: Context,var list: MutableList<WishlistModel>) :RecyclerView.Adapter<MyView3>()
{
    lateinit var apiinterface: Apiinterface
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView3
    {
        val view = LayoutInflater.from(context).inflate(R.layout.wishlist_design,parent,false)
        return MyView3(view)
    }

    override fun getItemCount(): Int
    {
        return list.size
    }

    override fun onBindViewHolder(holder: MyView3, position: Int)
    {
        sharedPreferences = this.context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
        var myid = list[position].id
        holder.textView1.text= list[position].gift_name
        holder.textView2.text = list[position].gift_price
        holder.textView3.text = list[position].gift_description
        Picasso.get().load(list[position].gift_image).into(holder.imageView)

        var mobile = sharedPreferences.getString("mobile","")
        apiinterface = ApiClient.getapiclient()!!.create(Apiinterface::class.java)

        holder.ib1.setOnClickListener {

            val call = apiinterface.deletewishlist(myid)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>)
                {
                    Toast.makeText(it.getContext(), "Delete", Toast.LENGTH_SHORT).show()
                    val i = Intent(context,WishlistActivity::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(i)
                }

                override fun onFailure(call: Call<Void>, t: Throwable)
                {
                    Toast.makeText(it.getContext(), "Error", Toast.LENGTH_SHORT).show()
                }

            })
        }

        holder.ib2.setOnClickListener {

            val call = apiinterface.addtocartdata(list[position].gift_name,list[position].gift_price,list[position].gift_description,list[position].gift_image,mobile)
            call.enqueue(object  : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>)
                {
                    Toast.makeText(context, "Add to Cart", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable)
                {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }


            })
        }
    }
}
class MyView3(itemView : View) : RecyclerView.ViewHolder(itemView)
{
    val imageView : ImageView = itemView.findViewById(R.id.whishlist_imageView)
    val textView1 : TextView = itemView.findViewById(R.id.whishlist_name_textview)
    val textView2 : TextView = itemView.findViewById(R.id.wishlist_price_textView)
    val textView3 : TextView = itemView.findViewById(R.id.wishlist_desc_textView)
    val ib1 = itemView.findViewById<ImageButton>(R.id.ib1)
    val ib2 = itemView.findViewById<ImageButton>(R.id.ib2)

}