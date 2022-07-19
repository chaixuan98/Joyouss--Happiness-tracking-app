package com.android.myapplication.todo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.RoomMasterTable


class ResultDBHelper (context: Context): SQLiteOpenHelper(context, "RESULT.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE RESULT (_id INTEGER PRIMARY KEY AUTOINCREMENT, MARK VARCHAR(256) , DATE VARCHAR(256))")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}