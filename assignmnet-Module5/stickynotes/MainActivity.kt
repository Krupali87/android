package com.example.stickynote

import android.annotation.SuppressLint
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.graphics.Paint
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.RemoteViews
import android.widget.Toast
import com.example.stickynote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    private lateinit var stickyNote : EditText
    private lateinit var databaseHelper: DatabaseHelper
    var currentsize : Float = 0.0F

    @SuppressLint("SuspiciousIndentation")

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        stickyNote = findViewById(R.id.edtnote)
        databaseHelper = DatabaseHelper(this)
        currentsize = stickyNote.textSize
        binding.txtsize.text = "$currentsize"

        binding.btnincresesize.setOnClickListener {

            binding.txtsize.text = "$currentsize"
            currentsize++
            binding.edtnote.textSize = currentsize

        }
        binding.btnreducesize.setOnClickListener{

            binding.txtsize.text = "$currentsize"
            currentsize--
            binding.edtnote.textSize = currentsize
        }
        binding.btnbold.setOnClickListener {

            if (binding.edtnote.typeface.isBold) {
                binding.edtnote.typeface = Typeface.DEFAULT
                binding.btnbold.setBackgroundColor(resources.getColor(R.color.purple_200))
                binding.btnbold.setTextColor(resources.getColor(R.color.white))
            } else {
                binding.btnbold.setBackgroundColor(resources.getColor(R.color.purple_200))
                binding.btnbold.setTextColor(resources.getColor(R.color.white))
                binding.edtnote.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            }
        }
        binding.btnunderline.setOnClickListener {

            if (binding.edtnote.paintFlags and Paint.UNDERLINE_TEXT_FLAG > 0)
            {
                binding.btnunderline.setBackgroundColor(resources.getColor(R.color.purple_200))
                binding.btnunderline.setTextColor(resources.getColor(R.color.white))
                binding.edtnote.paintFlags = binding.edtnote.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
            }
            else
            {
                binding.btnunderline.setBackgroundColor(resources.getColor(R.color.purple_200))
                binding.btnunderline.setTextColor(resources.getColor(R.color.white))
                binding.edtnote.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            }

        }
        binding.btnitalic.setOnClickListener {

            if (binding.edtnote.typeface.isItalic)
            {
                binding.edtnote.typeface = Typeface.DEFAULT
                binding.btnitalic.setBackgroundColor(resources.getColor(R.color.purple_200))
                binding.btnitalic.setTextColor(resources.getColor(R.color.white))
            }
            else
            {
                binding.btnitalic.setBackgroundColor(resources.getColor(R.color.purple_200))
                binding.btnitalic.setTextColor(resources.getColor(R.color.white))
                binding.edtnote.typeface = Typeface.defaultFromStyle(Typeface.ITALIC)
            }

        }
        binding.floating.setOnClickListener {

        val stickyNote = binding.edtnote.text.toString()
            val item = Model()
            item.setStickyNote(stickyNote)
            UpdateWidget()
            val i = Intent(applicationContext,MainActivity2::class.java)
            startActivity(i)

        }


    }
    private fun UpdateWidget()
    {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val remoteViews = RemoteViews(this.packageName,R.layout.widget_layout)
        val thisWidget = ComponentName(this,AppWidget::class.java)
        remoteViews.setTextViewText(R.id.idTVWidget,stickyNote.text.toString())
        appWidgetManager.updateAppWidget(thisWidget,remoteViews)
    }
}