package com.example.stickynote

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION)
{
    companion object
    {
        var DB_NAME = "stickynotes_db"
        var TABLE_NAME ="notes"
        var ID = "id"
        var STICKY_NOTE = "sticky_note"
        var DB_VERSION = 1

    }

    override fun onCreate(db: SQLiteDatabase?)
    {
        val query =
            "CREATE TABLE " + TABLE_NAME +
                    "(" + ID + " INTEGER PRIMARY KEY, " + STICKY_NOTE + " TEXT" + ")"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        val query = "DROP TABLE IF EXISTS $TABLE_NAME"
        db!!.execSQL(query)
    }
    fun insertStickyNotes(m :  Model) : Long
    {
        val db = writableDatabase
        val values= ContentValues()
        values.put(STICKY_NOTE,m.getStickyNote())
        return db.insert(TABLE_NAME,null,values)

    }
    fun updateStickyNotes(id : Int, stickyNote : String)
    {
        val db = writableDatabase
        val values = ContentValues()
        values.put(STICKY_NOTE,stickyNote )
        db.update(TABLE_NAME, values, "ID=?", arrayOf(id.toString()))
    }
    fun deleteStickyNote(id: Int)
    {
        val db = writableDatabase
        db.delete(TABLE_NAME, "ID=?", arrayOf(id.toString()))
    }
    @SuppressLint("Range")
    fun getAllStickyNotes() : MutableList<Model>
    {
        val db = writableDatabase
        var cursor : Cursor?= null
        val modellist =  ArrayList<Model>()
        db.beginTransaction()

            try {
                cursor = db.query(TABLE_NAME,null,null,null,null,null,null)
                if (cursor != null)
                {
                    if (cursor!!.moveToFirst())
                    {
                    do {
                        val stickyNote = Model()
                        stickyNote.setStickyNote(cursor!!.getString(cursor!!.getColumnIndex(STICKY_NOTE)))
                        stickyNote.setId(cursor!!.getInt(cursor!!.getColumnIndex(ID)))
                    } while (cursor!!.moveToFirst())
                }
            }
        }finally {
            db.endTransaction()
                cursor!!.close()
        }
            return modellist
    }
}