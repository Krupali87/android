package com.example.giftshopproject.Adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.giftshopproject.Activity.CartActivity
import com.example.giftshopproject.Activity.PaymentActivity
import com.example.giftshopproject.Activity.WishlistActivity
import com.example.giftshopproject.Interface.Apiinterface
import com.example.giftshopproject.Model.WishlistModel
import com.example.giftshopproject.R
import com.example.giftshopproject.client.ApiClient
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartAdapter(var context: Context,var list: MutableList<WishlistModel>) : RecyclerView.Adapter<MyView4>()
{
    lateinit var apiinterface: Apiinterface
   private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView4
    {
        val view = LayoutInflater.from(context).inflate(R.layout.cart_design,parent,false)
        return MyView4(view)
    }

    override fun getItemCount(): Int
    {
        return list.size
    }

    override fun onBindViewHolder(holder: MyView4, position: Int)
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

            val call = apiinterface.deletecart(myid)
            call.enqueue(object : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>)
                {
                    Toast.makeText(it.getContext(), "Delete", Toast.LENGTH_SHORT).show()
                    val i = Intent(context, CartActivity::class.java)
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

            var i = Intent(context,PaymentActivity::class.java)
            i.putExtra("id",list[position].id)
            i.putExtra("name",list[position].gift_name)
            i.putExtra("price",list[position].gift_price)
            i.putExtra("desc",list[position].gift_description)
            i.putExtra("image",list[position].gift_image)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }


    }
}
class MyView4(itemView : View) : RecyclerView.ViewHolder(itemView)
{
    val imageView : ImageView = itemView.findViewById(R.id.cart_imageView)
    val textView1 : TextView = itemView.findViewById(R.id.cart_name_textview)
    val textView2 : TextView = itemView.findViewById(R.id.cart_price_textView)
    val textView3 : TextView = itemView.findViewById(R.id.cart_desc_textView)
    val ib1 = itemView.findViewById<Button>(R.id.ib1)
    val ib2 = itemView.findViewById<Button>(R.id.ib2)

}
