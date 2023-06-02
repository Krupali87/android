package com.example.taskmanagement

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import android.text.format.DateFormat

class MyAdapter(var context: Context,var list: MutableList<Model>) : RecyclerView.Adapter<MyViewHolder>(),DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener
{
    private var dataBaseHealper: DataBaseHealper = DataBaseHealper(context)
    
    lateinit var calendar: Calendar
    var day = 0
     var month = 0
     var year = 0
     var hour = 0
    var minute = 0
     var dateTime = ""
     var myDay = 0
     var myMonth = 0
     var myYear = 0
    var myHour = 0
     var myMinute = 0
   var myDateTime = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.design,parent,false)
        return MyViewHolder(view)
    }

    private var position: Int = 0
    public fun getPosition(): Int {
        return position
    }
    public fun setPosition(position: Int) {
        this.position = position
    }

    override fun getItemCount(): Int
    {
       return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
       holder.taskName.text = list[position].taskname
        holder.taskDescription.text = list[position].taskdescription
        holder.taskPriority.text = list[position].taskpriority
        holder.dateTime.text = list[position].taskdatetime
        holder.taskStatus.text = list[position].taskstatus

        when(holder.taskPriority.text.toString())
        {
            "Low" ->
            {
                holder.cardView.setBackgroundColor(Color.GREEN)
            }
            "Medium" ->
            {
                holder.cardView.setBackgroundColor(Color.BLUE)
            }
            "High"->
            {
                holder.cardView.setBackgroundColor(Color.RED)
            }

        }
        holder.itemView.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
               setPosition(holder.getPosition())
                return false
            }
        })
        holder.itemView.setOnClickListener {
            context = holder.itemView.context
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Select operation")
            alertDialog.setPositiveButton("Update") { dialogInterface: DialogInterface, i: Int ->
                val alertDialog2 = AlertDialog.Builder(context)
                val view = LayoutInflater.from(context).inflate(
                    R.layout.dialogdesign,
                    null,
                    false
                )
                alertDialog2.setView(view)

                val taskName = view.findViewById<EditText>(R.id.dialog_edtTaskName)
                val taskDescription = view.findViewById<EditText>(R.id.dialog_edtTaskDescription)
                val btnSave =
                    view.findViewById<FloatingActionButton>(R.id.dialog_floatingSaveButton)
                val btnDateTimePicker = view.findViewById<Button>(R.id.dialog_btnDateTimePicker)
                val tvDateTime = view.findViewById<TextView>(R.id.dialog_tvDateTime)
                val rbLow = view.findViewById<RadioButton>(R.id.dialog_rbLow)
                val rbMedium = view.findViewById<RadioButton>(R.id.dialog_rbMedium)
                val rbHigh = view.findViewById<RadioButton>(R.id.dialog_rbHigh)
                var taskPriority: String = list[position].taskpriority
                when (taskPriority) {
                    "Low" -> {
                        rbLow.isChecked = true
                    }

                    "Medium" -> {
                        rbMedium.isChecked = true
                    }

                    else -> {
                        rbHigh.isChecked = true
                    }
                }
                taskName.setText(list[position].taskname)
                taskDescription.setText(list[position].taskdescription)
                tvDateTime.text = list[position].taskdatetime

                btnDateTimePicker.setOnClickListener {
                    calendar = Calendar.getInstance()
                    year = calendar.get(Calendar.YEAR)
                    month = calendar.get(Calendar.MONTH)
                    day = calendar.get(Calendar.DAY_OF_MONTH)
                    val datePickerDialog = DatePickerDialog(context, this, year, month, day)
                    datePickerDialog.show()
                }

                btnSave.setOnClickListener {
                    val taskNameString = taskName.text.toString()
                    val taskDescriptionString = taskDescription.text.toString()
                    myDateTime = dateTime
                    taskPriority = if (rbLow.isChecked) {
                        rbLow.text.toString()
                    } else if (rbMedium.isChecked) {
                        rbMedium.text.toString()
                    } else {
                        rbHigh.text.toString()
                    }
                    val m = Model()
                    m.id = list[position].id
                    m.taskname = taskNameString
                    m.taskdescription = taskDescriptionString
                    m.taskpriority = taskPriority
                    m.taskdatetime = myDateTime
                    dataBaseHealper.updateTask(list[position].id, m)
                    context.startActivity(Intent(context, MainActivity::class.java))
                }
                alertDialog2.show()
            }
            alertDialog.setNegativeButton("Delete") { dialogInterface: DialogInterface, i: Int ->
                dataBaseHealper.deleteData(list[position].id)
                context.startActivity(Intent(context, MainActivity::class.java))
            }
            alertDialog.show()
        }
    }
    override fun onViewRecycled(holder: MyViewHolder) {
        holder.itemView.setOnLongClickListener(null)
        super.onViewRecycled(holder)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int)
    {
        myDay = dayOfMonth
        myMonth = month
        myMonth += 1
        myYear = year
        calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(view!!.context, this, hour, minute, DateFormat.is24HourFormat(context))
        timePickerDialog.show()

    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int)
    {
        myHour = hourOfDay
        myMinute = minute
        dateTime = "$myYear-$myMonth-$myDay $myHour:$myMinute"
    }

    fun updateTaskStatus(position: Int) {
        val updatedTaskStatus = "COMPLETED"
        val m = Model()
        m.taskstatus = updatedTaskStatus
        dataBaseHealper.updateTaskStatus(list[position].id, m)
    }

}
class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),View.OnCreateContextMenuListener
{
    var taskName : TextView = itemView.findViewById(R.id.tvTaskName)
    var taskDescription : TextView = itemView.findViewById(R.id.tvTaskDescription)
    var dateTime : TextView = itemView.findViewById(R.id.tvDateTime)
    var taskPriority : TextView = itemView.findViewById(R.id.tvTaskPriority)
    var taskStatus : TextView = itemView.findViewById(R.id.tvTaskStatus)
    var cardView : CardView = itemView.findViewById(R.id.cardView)
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu!!.add(Menu.NONE,R.id.completeAction,Menu.NONE,R.string.complete_the_task)
    }
}