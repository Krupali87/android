package com.example.myapplication

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class projectAdapter(var context: Context, var list: MutableList<projectmodel>) : RecyclerView.Adapter<MyView>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView
    {
       var layout = LayoutInflater.from(context)
        var view= layout.inflate(R.layout.project_design,parent,false)
        return MyView(view)
    }

    override fun getItemCount(): Int
    {
       return list.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        val pid = list[position].id
        val pname = list[position].p_name
        val pdetail = list[position].p_detail

        holder.textView.text = pname
        holder.textView2.text = pdetail

        holder.itemView.setOnClickListener {

            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Select Operation")
            alertDialog.setPositiveButton("UPDATE") { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
                val intent = Intent(context, UpdateActivity::class.java)
                intent.putExtra("pName", pname)
                intent.putExtra("pDetails", pdetail)
                intent.putExtra("pId", pid)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context?.startActivity(intent)
            }
            alertDialog.setNegativeButton("DELETE") { dialogInterface: DialogInterface, i: Int ->

                dialogInterface.dismiss()
                val stringRequest = object : StringRequest(
                    Method.POST,
                    "https://krupalivaghela.000webhostapp.com/assignment/projectdelete.php",
                    Response.Listener {
                        Toast.makeText(context, "Project Deleted", Toast.LENGTH_SHORT).show()
                        context?.startActivity(Intent(context, viewActivity::class.java))
                    },
                    Response.ErrorListener {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }) {

                    override fun getParams(): MutableMap<String, String> {
                        val map = HashMap<String, String>()
                        map["id"] = pid
                        return map
                    }
                }
                val queue = Volley.newRequestQueue(context)
                queue.add(stringRequest)
            }
            alertDialog.show()
        }
    }

}
class MyView(itmView : View) : RecyclerView.ViewHolder(itmView)
{
    val textView : TextView= itmView.findViewById(R.id.txtname)
    val textView2 : TextView =itmView.findViewById(R.id.txtdetail)
}