package com.example.stickynote

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.graphics.Paint
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RemoteViews
import com.example.stickynote.databinding.ActivityMain3Binding


class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding
    private lateinit var stickyNote: EditText
    private lateinit var databaseHelper: DatabaseHelper
    var currentsize: Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        stickyNote = findViewById(R.id.edtupdatenote)
        currentsize = stickyNote.textSize
        binding.updatetxtsize.text = "$currentsize"
        val i = intent
        val oldStickyNoteId = i.getIntExtra("id", 0)
        val oldStickyNoteText = i.getStringExtra("stickynote")
        databaseHelper = DatabaseHelper(this)
        var isUpdate = false
        if (i != null) {
            isUpdate = true
            binding.edtupdatenote.setText(oldStickyNoteText)
        }
        binding.btnupdatebold.setOnClickListener {
            if (binding.edtupdatenote.typeface.isBold) {
                binding.edtupdatenote.typeface = Typeface.DEFAULT
                binding.btnupdatebold.setBackgroundColor(resources.getColor(R.color.purple_200))
                binding.btnupdatebold.setTextColor(resources.getColor(R.color.white))
            } else {
                binding.btnupdatebold.setBackgroundColor(resources.getColor(R.color.purple_200))
                binding.btnupdatebold.setTextColor(resources.getColor(R.color.white))
                binding.edtupdatenote.typeface = Typeface.defaultFromStyle(Typeface.BOLD)

            }
        }
            binding.btnupdateunderline.setOnClickListener {

                if (binding.edtupdatenote.paintFlags and Paint.UNDERLINE_TEXT_FLAG > 0)
                {
                    binding.btnupdateunderline.setBackgroundColor(resources.getColor(R.color.purple_200))
                    binding.btnupdateunderline.setTextColor(resources.getColor(R.color.white))
                    binding.edtupdatenote.paintFlags = binding.edtupdatenote.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
                }
                else
                {
                    binding.btnupdateunderline.setBackgroundColor(resources.getColor(R.color.purple_200))
                    binding.btnupdateunderline.setTextColor(resources.getColor(R.color.white))
                    binding.edtupdatenote.paintFlags = Paint.UNDERLINE_TEXT_FLAG
                }

            }
            binding.btnupdateitalic.setOnClickListener {

                if (binding.edtupdatenote.typeface.isItalic)
                {
                    binding.edtupdatenote.typeface = Typeface.DEFAULT
                    binding.btnupdateitalic.setBackgroundColor(resources.getColor(R.color.purple_200))
                    binding.btnupdateitalic.setTextColor(resources.getColor(R.color.white))
                }
                else
                {
                    binding.btnupdateitalic.setBackgroundColor(resources.getColor(R.color.purple_200))
                    binding.btnupdateitalic.setTextColor(resources.getColor(R.color.white))
                    binding.edtupdatenote.typeface = Typeface.defaultFromStyle(Typeface.ITALIC)
                }


            }
            binding.btnupdateincresesize.setOnClickListener {

                binding.updatetxtsize.text = "$currentsize"
                currentsize++
                binding.edtupdatenote.textSize = currentsize

            }
            binding.btnupdatereducesize.setOnClickListener {

                binding.updatetxtsize.text = "$currentsize"
                currentsize--
                binding.edtupdatenote.textSize = currentsize

            }
            binding.updatefloating.setOnClickListener {

                val newStickyNote = binding.edtupdatenote.text.toString()

                if (isUpdate)
                {
                    databaseHelper.updateStickyNotes(oldStickyNoteId,newStickyNote)
                }
                else
                {
                    val item = Model()
                    item.setStickyNote(newStickyNote)
                    databaseHelper.insertStickyNotes(item)
                }
                UpdateWidget()
                startActivity(Intent(applicationContext, MainActivity2::class.java))
            }


        }
    fun UpdateWidget()
    {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val remoteViews = RemoteViews(this.packageName,R.layout.widget_layout)
        val thisWidget = ComponentName(this,AppWidget::class.java)
        remoteViews.setTextViewText(R.id.idTVWidget,stickyNote.text.toString())
        appWidgetManager.updateAppWidget(thisWidget,remoteViews)
    }
    }
