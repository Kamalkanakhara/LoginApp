package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataHelper(context: Context) :
    SQLiteOpenHelper(context, "StudentDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

        val createTable = "CREATE TABLE student (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT)"

        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS student")
        onCreate(db)
    }

    fun insertData(name: String, password: String): Boolean {

        val db = this.writableDatabase
        val values = ContentValues()

        values.put("name", name)
        values.put("email", password)

        val result = db.insert("student", null, values)

        return result != -1L
    }
    fun getAllData(): android.database.Cursor {

        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM student", null)
        return cursor
    }
    fun updateData(id: String, name: String, password:String): Boolean {

        val db = this.writableDatabase
        val values = ContentValues()

        values.put("name", name)
        values.put("email", password)

        val result = db.update("student", values, "id=?", arrayOf(id))

        return result > 0
    }

    fun deleteData(id: String): Int {

        val db = this.writableDatabase
        return db.delete("student", "id=?", arrayOf(id))
    }
}