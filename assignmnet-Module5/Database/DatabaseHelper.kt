package com.example.todoapp2.Database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.todoapp2.Model.todoModel

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION)
{
        companion object
        {
            var DB_NAME = "Todo.db"
            var TABLE_NAME = "TaskList"
            var ID = "id"
            var TASK = "task"
            var STATUS = "status"
            var DB_VERSION = 1
        }

    override fun onCreate(db: SQLiteDatabase?)
    {
        var query ="CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TASK + " TEXT,"+ STATUS + " INTEGER " + ")"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        var upquery ="DROP TABLE IF EXISTS "+ TABLE_NAME
        db!!.execSQL(upquery)
        onCreate(db)
    }

    fun insertTask(m: todoModel): Long {
        val db = writableDatabase
        val values = ContentValues()
        values.put(TASK, m.getTask())
        values.put(STATUS, 0)
        return db.insert(TABLE_NAME, null, values)
    }

    fun updateTask(id: Int, task: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(TASK, task)
        db.update(TABLE_NAME, values, "ID=?", arrayOf(id.toString()))
    }

    fun updateStatus(id: Int, status: Int) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(STATUS, status)
        db.update(TABLE_NAME, values, "ID=?", arrayOf(id.toString()))
    }

    fun deleteTask(id: Int) {
        val db = writableDatabase
        db.delete(TABLE_NAME, "ID=?", arrayOf(id.toString()))
    }

    @SuppressLint("Range")
    fun getAllTasks(): MutableList<todoModel> {
        val db = writableDatabase
        var cursor: Cursor? = null
        val modelList = ArrayList<todoModel>()
        db.beginTransaction()
        try {
            cursor = db.query(TABLE_NAME, null, null, null, null, null, null)
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        val task = todoModel()
                        task.setId(cursor.getInt(cursor.getColumnIndex(ID)))
                        task.setTask(cursor.getString(cursor.getColumnIndex(TASK)))
                        task.setStatus((cursor.getInt(cursor.getColumnIndex(STATUS))))
                        modelList.add(task)
                    } while (cursor.moveToNext())
                }
            }
        } finally {
            db.endTransaction()
            cursor?.close()
        }
        return modelList
    }
}