package com.example.taskmanagement

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.example.taskmanagement.databinding.ActivityCreatetaskBinding
import java.util.*

class createtaskActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    lateinit var binding: ActivityCreatetaskBinding
    private lateinit var dataBaseHealper: DataBaseHealper
    private var calendar: Calendar = Calendar.getInstance()
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0
    var datetime = ""
    var myDay = 0
    var myMonth = 0
    var myYear = 0
    var myHour = 0
    var myMinute = 0
    var myDateTime = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatetaskBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        dataBaseHealper = DataBaseHealper(applicationContext)

        binding.btnDateTimePicker.setOnClickListener {
            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(this, this, year, month, day)
            datePickerDialog.show()
        }
        binding.floatingSaveButton.setOnClickListener {
            var taskName = binding.edtTaskName.text.toString()
            var taskDescription = binding.edtTaskDescription.text.toString()
            myDateTime = datetime
            var taskPriority: String
            if (binding.rbLow.isChecked) {
                taskPriority = binding.rbLow.text.toString()
            } else if (binding.rbMedium.isChecked) {
                taskPriority = binding.rbMedium.text.toString()
            } else {
                taskPriority = binding.rbHigh.text.toString()
            }
            val m = Model()
            m.taskname = taskName
            m.taskdescription = taskDescription
            m.taskdatetime = myDateTime
            m.taskpriority = taskPriority
            dataBaseHealper.insertTask(m)
            Toast.makeText(applicationContext, "Task Added", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = dayOfMonth
        myMonth = month
        myMonth += 1
        myYear = year
        calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(
            this,
            this,
            hour,
            minute,
            DateFormat.is24HourFormat(this)
        )
        timePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        myHour = hourOfDay
        myMinute = minute
        datetime = "$myYear-$myMonth-$myDay $myHour:$myMinute"
        binding.tvDateTime.text =
            datetime
    }
}