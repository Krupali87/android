package com.example.taskmanagement

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.recyclerview.widget.RecyclerView

class DataBaseHealper (context: Context) : SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION)
{
    companion object
    {
        var DB_NAME = "TaskManagement"
        var TABLE_NAME = "Tasks"
        var ID = "id"
        var TASK_NAME = "task_name"
        var TASK_DESCRIPTION = "task_description"
        var TASK_PRIORITY = "task_priority"
        var TASK_DATE_TIME = "task_date_time"
        var TASK_STATUS = "task_status"
        var DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?)
    {
        val query = "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY, $TASK_NAME TEXT, $TASK_DESCRIPTION TEXT,  $TASK_PRIORITY TEXT,  $TASK_DATE_TIME TEXT,  $TASK_STATUS TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        val query = "DROP TABLE IF EXISTS $TABLE_NAME"
        db!!.execSQL(query)
    }

    fun insertTask(m : Model) :Long
    {
        val db = writableDatabase
        val values = ContentValues()
        values.put(TASK_NAME, m.taskname)
        values.put(TASK_DESCRIPTION, m.taskdescription)
        values.put(TASK_PRIORITY, m.taskpriority)
        values.put(TASK_DATE_TIME, m.taskdatetime)
        values.put(TASK_STATUS, m.taskstatus)
        return db.insert(TABLE_NAME, ID, values)
    }
    fun viewTasks(): MutableList<Model> {
        val db = readableDatabase
        val mutableList = ArrayList<Model>()
        val colArray = arrayOf(ID, TASK_NAME, TASK_DESCRIPTION, TASK_PRIORITY, TASK_DATE_TIME, TASK_STATUS)
        val cursor = db.query(TABLE_NAME, colArray, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val taskname = cursor.getString(1)
            val taskdescription = cursor.getString(2)
            val taskpriority = cursor.getString(3)
            val taskdatetime = cursor.getString(4)
            val taskstatus = cursor.getString(5)
            val m = Model()
            m.id = id
            m.taskname = taskname
            m.taskdescription = taskdescription
            m.taskpriority = taskpriority
            m.taskdatetime = taskdatetime
            m.taskstatus = taskstatus
            mutableList.add(m)
        }
        return mutableList
    }

    fun updateTask(id: Int, m: Model) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(TASK_NAME, m.taskname)
        values.put(TASK_DESCRIPTION, m.taskdescription)
        values.put(TASK_PRIORITY, m.taskpriority)
        values.put(TASK_DATE_TIME, m.taskdatetime)
        values.put(TASK_STATUS, m.taskstatus)
        db.update(TABLE_NAME, values, "ID=?", arrayOf(id.toString()))
    }

    fun updateTaskStatus(id: Int, m: Model) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(TASK_STATUS, m.taskstatus)
        db.update(TABLE_NAME, values, "ID=?", arrayOf(id.toString()))
    }

    fun deleteData(id: Int) {
        val db = writableDatabase
        db.delete(TABLE_NAME, "ID=?", arrayOf(id.toString()))
    }
}